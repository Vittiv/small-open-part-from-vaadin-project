package com.javamentor.qa.platform.frontendvaadin.util;

import com.javamentor.qa.platform.frontendvaadin.dto.TagRecentDto;
import com.javamentor.qa.platform.frontendvaadin.entity.question.Tag;
//import org.mapstruct.Mapper;

import java.util.List;

//@Mapper
public interface TagMapper {

    List<TagRecentDto> tagToTagRecentDtos(List<Tag> tags);

    TagRecentDto tagToTagRecentDto(Tag tags);

}
