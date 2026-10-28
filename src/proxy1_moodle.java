interface Image {
    void show();
}

class RealImage implements Image {
    String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    void loadFromDisk() {
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }

    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}

class ProxyImage implements Image {
    RealImage realImage;
    String fileName;
    String username;

    public ProxyImage(String fileName, String username) {
        this.fileName = fileName;
        this.username = username;
    }

    public void show() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        if(!username.equals("ADMIN")){
            System.out.println("[ACCESS DENIED] You lack permission to view:" + fileName + "and return immediately");
        }
        else{
            realImage.show();
        }
    }
}

public class proxy1_moodle {
    public static void main(String[] args) {
        Image photo = new ProxyImage("dog.png", "ADMIN");
        Image phot = new ProxyImage("dog.png", "SOMEONE");
        photo.show();
        phot.show();

        System.out.println("Step 1 done: Proxy created. Still zero disk activity.\n");

        System.out.println("Step 2: Calling show() for the FIRST time:");
        photo.show();

        System.out.println("\nStep 3: Calling show() for the SECOND time:");
        photo.show();
    }
}
