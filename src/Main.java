import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sinre
 * @create 01 15, 2023
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        new MyFrame("go mod UI");
    }
}

class MyFrame extends JFrame {
    public MyFrame(String title) {
        // 得到显示器屏幕的宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        // 定义窗体的宽高
        int windowsWedth = 800;
        int windowsHeight = 800;

        this.setTitle(title);

        JPanel root = new JPanel();
        root.setLayout(new GridLayout(3,2));

        this.setLocationRelativeTo(null);
        this.setContentPane(root);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(windowsWedth, windowsHeight);
        this.setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        this.setVisible(true);

        // 创建按钮
        JButton ginBtn = new JButton("<html>---gin---<br>go get -u<br>github.com/gin-gonic/gin</html>");
        // 设置按钮字体大小20
        ginBtn.setFont(new Font(ginBtn.getFont().getName(), ginBtn.getFont().getStyle(), 20));
        // 添加事件
        ginBtn.addActionListener(e -> ex("go get -u github.com/gin-gonic/gin"));

        JButton gormBtn = new JButton("<html>---gorm---<br>go get -u<br>gorm.io/gorm</html>");
        gormBtn.setFont(new Font(gormBtn.getFont().getName(), gormBtn.getFont().getStyle(), 20));
        gormBtn.addActionListener(e -> ex("go get -u gorm.io/gorm"));

        JButton testifyBtn = new JButton("<html>---testify---<br>go get -u<br>github.com/stretchr/testify</html>");
        testifyBtn.setFont(new Font(testifyBtn.getFont().getName(), testifyBtn.getFont().getStyle(), 20));
        testifyBtn.addActionListener(e -> ex("go get -u github.com/stretchr/testify"));

        root.add(ginBtn);
        root.add(gormBtn);
        root.add(testifyBtn);
    }

    public void ex(String cmd) {
        Runtime mt = Runtime.getRuntime();
        try {
            Process pro = mt.exec(cmd);
            InputStream inputStream = pro.getInputStream();
            String read = read(inputStream);
            if (read.length() == 0)
                JOptionPane.showMessageDialog(this, cmd + " ==> successfully");
            else
                JOptionPane.showMessageDialog(this, read);
            inputStream.close();
            pro.waitFor();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }

    public String read(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[100];
        while(in.read(bytes)!=-1){
            for (byte b : bytes) {
                sb.append((char)b);
            }
        }
        return sb.toString();
    }
}
