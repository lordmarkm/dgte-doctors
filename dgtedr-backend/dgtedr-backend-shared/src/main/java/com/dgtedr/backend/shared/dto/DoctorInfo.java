package com.dgtedr.backend.shared.dto;

import com.efs.core.dto.BaseInfo;
import com.efs.core.dto.NameInfo;

/**
 *
 * @author mbmartinez on 8 Jun 2018
 *
 */
public class DoctorInfo extends BaseInfo {

    private NameInfo name;
    private DoctorCredentialsInfo credentials;

    public NameInfo getName() {
        return name;
    }
    public void setName(NameInfo name) {
        this.name = name;
    }
    public DoctorCredentialsInfo getCredentials() {
        return credentials;
    }
    public void setCredentials(DoctorCredentialsInfo credentials) {
        this.credentials = credentials;
    }

}
