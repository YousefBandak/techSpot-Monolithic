package object_orienters.techspot.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import object_orienters.techspot.content.Content;
import object_orienters.techspot.model.ContentType;
import object_orienters.techspot.model.Privacy;
import object_orienters.techspot.profile.Profile;

@Entity
@Getter
@Setter
public class SharedPost extends Content {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonBackReference
    private Profile sharer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;


    public SharedPost(Profile sharer, Post post, Privacy privacy) {
        this.sharer = sharer;
        this.post = post;
        this.setPrivacy(privacy);
        this.setContentType(ContentType.SharedPost);
    }

    public SharedPost() {
        this.setContentType(ContentType.SharedPost);
    }


    @Override
    public Profile getMainAuthor() {
        return getSharer();
    }

    @Override
    public ContentType getContentType() {
        return ContentType.SharedPost;
    }
}
