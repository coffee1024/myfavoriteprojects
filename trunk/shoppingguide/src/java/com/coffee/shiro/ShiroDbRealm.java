/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.coffee.shiro;

import java.io.Serializable;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Objects;

public class ShiroDbRealm extends AuthorizingRealm {

	
	public  static final String CAPTCHAKEY="captcha";
	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println(token);
		
//		Object captcha= SecurityUtils.getSubject().getSession().getAttribute(CAPTCHAKEY);
//		
//		if (captcha==null||org.apache.commons.lang.StringUtils.isBlank(captcha.toString())||StringUtils.isBlank(token.getCaptcha())||!StringUtils.equals(captcha.toString(), token.getCaptcha())) {
//			 throw new IncorrectCaptchaException("验证码错误！");
//		}
//		System.out.println("验证码通过");
		
//		User user = accountService.findUserByLoginName(token.getUsername());
			AuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(new ShiroUser(1L, token.getUsername()),
					"admin",getName());
			return authenticationInfo;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
//		System.out.println(user);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addRoles(user.getRoleList());
//		info.addStringPermissions(user.getPermissionList());
		return info;
	}



	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public Long id;
		public String name;

		public ShiroUser(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return name;
		}

		/**
		 * 重载hashCode,只计算name;
		 */
		@Override
		public int hashCode() {
			return Objects.hashCode(name);
		}

		/**
		 * 重载equals,只计算name;
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ShiroUser other = (ShiroUser) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
	}
}
