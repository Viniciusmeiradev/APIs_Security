package twitter.sistem.springsecurity.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
