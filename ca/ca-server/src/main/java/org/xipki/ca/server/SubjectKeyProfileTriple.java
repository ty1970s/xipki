/*
 * Copyright (c) 2014 Lijun Liao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 *
 */

package org.xipki.ca.server;

/**
 * @author Lijun Liao
 */

public class SubjectKeyProfileTriple
{
    private final int certId;
    private final String subjectFp;
    private final String keyFp;
    private final String profile;
    private final boolean revoked;

    public SubjectKeyProfileTriple(int certId, String subjectFp, String keyFp, String profile, boolean revoked)
    {
        super();
        this.certId = certId;
        this.subjectFp = subjectFp;
        this.keyFp = keyFp;
        this.profile = profile;
        this.revoked = revoked;
    }

    public int getCertId()
    {
        return certId;
    }

    public String getSubjectFp()
    {
        return subjectFp;
    }

    public String getKeyFp()
    {
        return keyFp;
    }

    public String getProfile()
    {
        return profile;
    }

    public boolean isRevoked()
    {
        return revoked;
    }
}
