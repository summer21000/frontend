package com.example.News.mapper;

import com.example.News.domain.News;
import com.example.News.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper {
    @Mapping(target = "newsId", ignore = true)
    News newPostDtoToNews(NewsDto.Post pos);

    @Mapping(target = "newsId", ignore = true)
    void PatchDtoToNews(NewsDto.Patch patch, @MappingTarget News news);
}
