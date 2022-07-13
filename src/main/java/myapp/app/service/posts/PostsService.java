package myapp.app.service.posts;

import lombok.RequiredArgsConstructor;
import myapp.app.domain.posts.Posts;
import myapp.app.domain.posts.PostsRepository;
import myapp.app.web.dto.posts.PostsResponseDto;
import myapp.app.web.dto.posts.PostsSaveRequestDto;
import myapp.app.web.dto.posts.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(posts);
    }
}
