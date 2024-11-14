// -------------- Parent Class của tất cả các Library Resource---------------------------------
public class LibraryResource {
    // Tên của Library Resource, Phải Unique
    String resourceName;

    // unique id của một library resource
    proteced int resourceID;

    int type;

    // trả về một unique id của resource
    public int getResourceID() {
        return this.resourceID;
    }
}