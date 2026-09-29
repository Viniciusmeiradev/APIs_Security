package twitter.sistem.springsecurity.controller.dto;

public record FeedDto(List<FeedItemDto>feedItens, int page, int pageSize, int totalPages, int totalElements) {
    
}
