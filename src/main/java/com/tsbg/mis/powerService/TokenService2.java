package com.tsbg.mis.powerService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tsbg.mis.powerModel.TokenBlacklist2;
import com.tsbg.mis.powerModel.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 下发
 */
@Service("TokenService2")
public class TokenService2 {

	@Autowired
	private TokenBlacklistService2 tokenBlackService;

	public String getToken(UserInfo userInfo) {
		Date start = new Date();
		long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
		Date end = new Date(currentTime);
		String token = "";
		
		token = JWT.create().withAudience(userInfo.getUserId().toString()).withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(userInfo.getUserPwd()));
		//在数据库插入token
		TokenBlacklist2 tokenBlacklist = new TokenBlacklist2();
		tokenBlacklist.setTokenCode(token);
		tokenBlackService.insertSelective(tokenBlacklist);
		return token;
	}
}
