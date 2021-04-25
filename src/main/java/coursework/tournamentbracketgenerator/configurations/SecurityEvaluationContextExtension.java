package coursework.tournamentbracketgenerator.configurations;

import org.springframework.data.spel.spi.EvaluationContextExtension;
import org.springframework.lang.NonNull;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class SecurityEvaluationContextExtension implements EvaluationContextExtension {

    @Override
    @NonNull
    public String getExtensionId() {
        return "security";
    }

    @Override
    public SecurityExpressionRoot getRootObject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new SecurityExpressionRoot(authentication) {
        };
    }
}
