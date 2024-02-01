// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.domains;

import java.util.ArrayList;
import java.util.List;

import chatApplication.files.Audio;
import chatApplication.files.Image;
import chatApplication.files.Video;

public abstract class Group {
    private List<Video> _videos = new ArrayList<>();
    private List<Audio> _audios = new ArrayList<>();
    private List<Image> _images = new ArrayList<>();
    private String _nameGroup;

    public String getNameGroup() {
        return _nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this._nameGroup = nameGroup;
    }

    public Group(String nameGroup) {
        _nameGroup = nameGroup;
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
    
    public abstract List<User> getUsersByName(String name);
}
