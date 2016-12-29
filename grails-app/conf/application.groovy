

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'cvut.fel.via.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'cvut.fel.via.UserRole'
grails.plugin.springsecurity.authority.className = 'cvut.fel.via.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]


grails.plugin.springsecurity.interceptUrlMap = [
		[pattern: '/static/**',      access: ['permitAll']],
		[pattern: '/main/**',  		 access: ['isAuthenticated()']],
		[pattern: '/restaurant/**',  access: ['isAuthenticated()']],
		[pattern: '/',				 access: ['isAuthenticated()']],
		[pattern: '/api/**',         access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/login',          access: ['permitAll']],
		[pattern: '/login/**',       access: ['permitAll']],
		[pattern: '/logout',         access: ['permitAll']],
		[pattern: '/logout/**',      access: ['permitAll']]
]

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
