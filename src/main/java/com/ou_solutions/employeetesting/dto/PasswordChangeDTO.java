package com.ou_solutions.employeetesting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeDTO {
	
	private String email;
	
	private String currentPassword;
	
	private String newPassword;

}
