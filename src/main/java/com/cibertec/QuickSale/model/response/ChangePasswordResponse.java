package com.cibertec.QuickSale.model.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ChangePasswordResponse implements Serializable {
    private String email;
    private String currentPassword;
    private String newPassword;
}
