package chatApplication.domains.groupimpl;

import chatApplication.files.Audio;
import chatApplication.files.Image;
import chatApplication.files.Video;

import java.util.ArrayList;
import java.util.List;

public class EntityGroup {
    private List<Video> _videos = new ArrayList<>();
    private List<Audio> _audios = new ArrayList<>();
    private List<Image> _images = new ArrayList<>();
}
