package capers;

import java.io.File;
import static capers.Utils.*;

/** A repository for Capers 
 * @author Gan
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Utils.join(CWD, ".capers");
                                            //      function in Utils

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdirs();
        }
        if (!Dog.DOG_FOLDER.exists()) {
            Dog.DOG_FOLDER.mkdirs();
        }
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        // 1. 找到 story 文件的位置
        File storyFile = Utils.join(CAPERS_FOLDER, "story");

        // 2. 读取旧的故事（如果文件存在）
        String oldStory = "";
        if (storyFile.exists()) {
            oldStory = Utils.readContentsAsString(storyFile);
        }

        // 3. 拼接新内容
        String newStory = oldStory + text + "\n";

        // 4. 写回文件并打印
        Utils.writeContents(storyFile, newStory);
        System.out.print(newStory);
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog newDog = new Dog(name, breed, age);

        // 2. 调用你刚刚写好的 saveDog 方法，把这只狗持久化到硬盘里
        newDog.saveDog();

        // 3. 打印这只狗的信息 (文档要求打印它的 toString())
        System.out.println(newDog.toString());
        // 或者写 System.out.println(newDog.toString()); 效果一样
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        // 1. 根据名字，从硬盘里把这只狗“捞”出来
        Dog dog = Dog.fromFile(name);

        // 2. 调用 Dog 类里现成的方法，让它的年龄 +1
        dog.haveBirthday();

        // 3. 【极其重要】把这只长大了一岁的狗，重新存回硬盘覆盖旧数据！
        // 如果不写这一行，下次运行程序时，这只狗的年龄还是没变。
        dog.saveDog();
    }
}
