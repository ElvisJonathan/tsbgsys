package com.tsbg.ecosys.ecoService.base;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.tsbg.ecosys.ecoModel.UserInfo;
import com.tsbg.ecosys.ecoModel.TokenBlacklist;
import com.tsbg.ecosys.ecoService.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
 */
@Service("TokenService")
public class TokenService {

	@Autowired
	private TokenBlacklistService tokenBlackService;

	public String getToken(UserInfo userInfo) {
		Date start = new Date();
		long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
		Date end = new Date(currentTime);
		String token = "";
		
		token = JWT.create().withAudience(userInfo.getUserId().toString()).withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(userInfo.getUserPwd()));
		//在数据库插入token
		TokenBlacklist tokenBlacklist = new TokenBlacklist();
		tokenBlacklist.setTokenCode(token);
		tokenBlackService.insertSelective(tokenBlacklist);
		return token;
	}
}
