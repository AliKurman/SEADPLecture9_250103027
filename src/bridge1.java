// ==========================================
// TEAM 1: THE SENDER (HOW IT TRAVELS)
// ==========================================

// The rule for anything that can send text
interface MessageSender {
    // Every sender must have a deliver method
    void deliver(String text);
}

// Concrete sender 1: Email
class EmailSender implements MessageSender {
    // Implementation for sending an email
    public void deliver(String text) {
        // Pretend this sends an actual email
        System.out.println("Sending via Email: " + text);
    }
}

// Concrete sender 2: SMS
class SmsSender implements MessageSender {
    // Implementation for sending a text message
    public void deliver(String text) {
        // Pretend this sends an actual SMS
        System.out.println("Sending via SMS: " + text);
    }
}


// ==========================================
// TEAM 2: THE MESSAGE (WHAT IT SAYS)
// ==========================================

// Base message class
abstract class Message {
    // THIS VARIABLE IS THE BRIDGE:
    // It links the Message team to the Sender team
    protected MessageSender sender;

    // You plug a sender into this slot when you create the message
    public Message(MessageSender sender) {
        // Save the chosen sender
        this.sender = sender;
    }

    // Every message type must write its own send method
    abstract void send(String content);
}

// Concrete message type 1: Normal message
class NormalMessage extends Message {
    // Pass the sender up to the parent slot
    public NormalMessage(MessageSender sender) {
        // Calls the parent constructor to store the sender
        super(sender);
    }

    // Normal messages send text as-is
    public void send(String content) {
        // Use the bridge to deliver the untouched text
        sender.deliver(content);
    }
}

// Concrete message type 2: Urgent message
class UrgentMessage extends Message {
    // Pass the sender up to the parent slot
    public UrgentMessage(MessageSender sender) {
        // Calls the parent constructor to store the sender
        super(sender);
    }

    // Urgent messages tamper with the text first
    public void send(String content) {
        // Add alert tags before passing it across the bridge
        sender.deliver("[URGENT ALERT] " + content);
    }
}


// ==========================================
// RUNNING THE DEMO
// ==========================================
public class bridge1 {
    public static void main(String[] args) {

        // Mix and match 1: Normal message via Email
        Message msg1 = new NormalMessage(new EmailSender());
        // Deliver it
        msg1.send("Server backup finished.");

        // Mix and match 2: Urgent message via SMS
        Message msg2 = new UrgentMessage(new SmsSender());
        // Deliver it
        msg2.send("Server is on fire!");

        // Mix and match 3: Urgent message via Email
        Message msg3 = new UrgentMessage(new EmailSender());
        // Deliver it
        msg3.send("Database connection lost!");
    }
}
