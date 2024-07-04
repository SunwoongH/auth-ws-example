package example.support.auth.config

import example.support.auth.PrincipalUserArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
internal class WebConfig(
    private val principalUserArgumentResolver: PrincipalUserArgumentResolver,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(principalUserArgumentResolver)
    }
}
