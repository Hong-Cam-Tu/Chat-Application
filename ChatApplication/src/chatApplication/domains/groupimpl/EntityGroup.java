package chatApplication.domains.groupimpl;

import chatApplication.files.Audio;
import chatApplication.files.Image;
import chatApplication.files.Video;

import java.util.ArrayList;
import java.util.List;

public class EntityGroup {
    private String _idFile;
    private List<Video> _videos = new ArrayList<>();
    private List<Audio> _audios = new ArrayList<>();
    private List<Image> _images = new ArrayList<>();

    public EntityGroup(String id) {
        _idFile= id;
    }

    public String getId() {
        return _idFile;
    }
    public List<Image> getImage() {
        return _images;
    }
    public void setImage(Image image) {
        _images.add(image);
    }
    public List<Video> getVideos() {
        return _videos;
    }
    public void addVideos(Video video) {
        _videos.add(video);
    }
    public List<Audio> getAudios() {
        return _audios;
    }
    public void setAudios(Audio audios) {
        _audios.add(audios);
    }
}
