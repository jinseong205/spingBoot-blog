package com.jinseong.blog.model;

import lombok.Data;

@Data
public class KakaoProfile {

	public Long id;
	public String connectedAt;
	public Properties properties;
	public KakaoAccount kakaoAccount;
	
	@Data
	class Properties {
		public String nickname;
		public String thumbnamilImageUrl;
		public String profileImageUrl;
	}
	
	@Data
	class KakaoAccount {

		public Boolean profileNicknameNeedsAgreement;
		public Profile profile;
		public Boolean hasEmail;
		public Boolean emailNeedsAgreement;
		public Boolean isEmailValid;
		public Boolean isEmailVerified;
		public String email;
		
		@Data
		class Profile {
			public String nickname;
			public String thumbnamilImageUrl;
			public String profileImageUrl;
		}
	}

}
