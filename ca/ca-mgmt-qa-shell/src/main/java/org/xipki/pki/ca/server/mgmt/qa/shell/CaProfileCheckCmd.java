/*
 *
 * This file is part of the XiPKI project.
 * Copyright (c) 2013 - 2016 Lijun Liao
 * Author: Lijun Liao
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
 * THE AUTHOR LIJUN LIAO. LIJUN LIAO DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
 * OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the XiPKI software without
 * disclosing the source code of your own applications.
 *
 * For more information, please contact Lijun Liao at this
 * address: lijun.liao@gmail.com
 */

package org.xipki.pki.ca.server.mgmt.qa.shell;

import java.rmi.UnexpectedException;
import java.util.Map;

import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.xipki.commons.console.karaf.CmdFailure;
import org.xipki.pki.ca.server.mgmt.shell.CaCommandSupport;
import org.xipki.pki.ca.server.mgmt.shell.completer.CaNameCompleter;
import org.xipki.pki.ca.server.mgmt.shell.completer.ProfileNameCompleter;

/**
 * @author Lijun Liao
 * @since 2.0.0
 */

@Command(scope = "xipki-caqa", name = "caprofile-check",
    description = "check information of certificate profiles in given CA (QA)")
@Service
public class CaProfileCheckCmd extends CaCommandSupport {

  @Option(name = "--ca",
      required = true,
      description = "CA name\n"
          + "(required)")
  @Completion(CaNameCompleter.class)
  private String caName;

  @Option(name = "--profile",
      required = true,
      description = "profile profileName\n"
        + "(required)")
  @Completion(ProfileNameCompleter.class)
  private String profileName;

  @Option(name = "--local-name",
      required = false,
      description = "profile localname")
  private String profileLocalname;

  @Override
  protected Object doExecute()
  throws Exception {
    out("checking CA profile CA='" + caName +  "', profile='" + profileName + "'");

    if (caManager.getCA(caName) == null) {
      throw new UnexpectedException("could not find CA '" + caName + "'");
    }

    if (profileLocalname == null) {
      profileLocalname = profileName;
    }
    Map<String, String> entries = caManager.getCertprofilesForCA(caName);
    if (!entries.containsKey(profileLocalname)) {
      throw new CmdFailure("CA is not associated with profile '" + profileLocalname + "'");
    }

    String name = entries.get(profileLocalname);
    if (!profileName.equals(name)) {
      throw new CmdFailure(
          "Profile name is '" + name + "', but expected '" + profileName + "'");
    }
    out(" checked CA profile CA='" + caName +  "', profile='" + profileName + "'");
    return null;
  }

}
