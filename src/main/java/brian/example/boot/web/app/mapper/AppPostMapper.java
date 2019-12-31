package brian.example.boot.web.app.mapper;

import brian.example.boot.web.app.command.PostCommand;
import brian.example.boot.web.app.domain.AppPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")    // If you want to use DI for maapping, (componentModel="spring") is necessary
public interface AppPostMapper {

    AppPostMapper INSTANCE = Mappers.getMapper(AppPostMapper.class);

    // This is for example.
    // If the source and the target have the same names, @Mappings/@Mapping is not required
    @Mapping(source = "subject", target = "subject")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "postId", target = "postId")
    @Mapping(source = "userId", target = "userId")
    PostCommand toPostCommand(AppPost appPost);
}
