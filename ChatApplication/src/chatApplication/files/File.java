// Source code is decompiled from a .class file using FernFlower decompiler.
package chatApplication.files;

public abstract class File {
    private double _capacity;
    
    public File(double _capacity) {
        this._capacity = _capacity;
    }

    public abstract File build();

    public double getCapacity() {
        return _capacity;
    }

    public void setCapacity(double capacity) {
        _capacity = capacity;
    }

}
