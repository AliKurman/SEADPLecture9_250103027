interface Video {
    void play();
}

class RealVideo implements Video {
    String title;

    public RealVideo(String title) {
        this.title = title;
        bufferVideo();
    }

    void bufferVideo() {
        System.out.println("--> [DOWNLOADING] Connecting to server & buffering 4K video: " + title);
    }

    public void play() {
        System.out.println("Playing video: " + title);
    }
}

class ProxyVideo implements Video {
    RealVideo realVideo;
    String title;
    int viewcount;

    public ProxyVideo(String title) {
        this.title = title;
        viewcount = 0;
    }

    public void play() {
        if (realVideo == null) {
            realVideo = new RealVideo(title);
        }
        viewcount += 1;
        realVideo.play();
        System.out.println(" --> [AUDIT] Video has been played " + viewcount +" time(s).");

    }
}

public class proxy2_moodle {
    public static void main(String[] args) {
        Video video1 = new ProxyVideo("Cat_Memes_4K.mp4");
        Video video2 = new ProxyVideo("Java_Tutorial_4K.mp4");
        Video video3 = new ProxyVideo("LoFi_Study_Beats_4K.mp4");

        System.out.println("Step 1: Created 3 videos. Zero data downloaded so far.\n");

        System.out.println("Step 2: User clicks play on Cat Memes for the FIRST time:");
        video1.play();

        System.out.println("\nStep 3: User clicks play on Cat Memes for the SECOND time:");
        video1.play();

        System.out.println("\nStep 4: Check video 2 and video 3:");
        System.out.println("Notice: 'Java_Tutorial_4K.mp4' and 'LoFi_Study_Beats_4K.mp4' were NEVER downloaded!");
    }
}
