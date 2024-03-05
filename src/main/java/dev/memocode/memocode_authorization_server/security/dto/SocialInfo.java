package dev.memocode.memocode_authorization_server.security.dto;

import dev.memocode.memocode_authorization_server.domain.account.entity.AuthType;
import dev.memocode.memocode_authorization_server.exception.GlobalErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Optional;

import static dev.memocode.memocode_authorization_server.domain.account.entity.AuthType.KAKAO;

// oauth2 login에서만 사용하기를 권장
@Getter
@Builder
@AllArgsConstructor
public class SocialInfo {

    private final AuthType type;

    private final String id;

    private final String email;

    public static SocialInfo createSocialInfo(String registrationId, OAuth2User oAuth2User) {

        Optional<AuthType> _socialInfoType = AuthType.fromString(registrationId);

        if (_socialInfoType.isEmpty()) {
            throw new OAuth2AuthenticationException(new OAuth2Error(
                    GlobalErrorCode.UNSUPPORTED_SOCIAL_PLATFORM.name(),
                    "%s는 지원되지 않는 로그인 방식입니다.".formatted(registrationId),
                    ""));
        }

        return switch (_socialInfoType.get()) {
            case KAKAO -> createKakaoSocialInfo(oAuth2User);
            case EMAIL -> throw new OAuth2AuthenticationException(new OAuth2Error(
                    GlobalErrorCode.UNSUPPORTED_SOCIAL_PLATFORM.name(),
                    "이메일로 소셜로그인을 진행할 수 없습니다.",
                    ""));
        };
    }

    public static SocialInfo createKakaoSocialInfo(OAuth2User oAuth2User) {

        Map<String, Object> properties = oAuth2User.getAttribute("properties");
        Map<String, Object> kakao_account = oAuth2User.getAttribute("kakao_account");

        if (properties == null || kakao_account == null) {
            String missingAttribute = properties == null ? "properties" : "kakao_account";
            throw new OAuth2AuthenticationException(new OAuth2Error(
                    GlobalErrorCode.UNEXPECTED_API_RESPONSE.name(),
                    "createKakaoSocialInfo에서 %s가 null입니다.".formatted(missingAttribute),
                    ""));
        }

        String id = oAuth2User.getName();
        String email = (String) kakao_account.getOrDefault("email", "");

        if (id.isBlank() || email.isBlank()) {
            String blankAttribute = id.isBlank() ? "id" : "email";
            throw new OAuth2AuthenticationException(new OAuth2Error(
                    "unexpected_api_response",
                    "createKakaoSocialInfo메서드에서 %s이 blank입니다.".formatted(blankAttribute),
                    ""));
        }

        return SocialInfo.builder()
                .id(id)
                .type(KAKAO)
                .email(email)
                .build();
    }
}
