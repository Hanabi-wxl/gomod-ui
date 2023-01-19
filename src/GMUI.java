import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sinre
 * @create 01 15, 2023
 * @since 1.0.0
 */
public class GMUI {
    public static JFrame frame = new JFrame("GoModUi");
    public static void main(String[] args) {
        // 得到显示器屏幕的宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        // 定义窗体的宽高
        int windowsWedth = 800;
        int windowsHeight = 800;

        JPanel root = new JPanel();
        root.setLayout(new GridLayout(4,2));

        frame.setLocationRelativeTo(null);
        frame.setContentPane(root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowsWedth, windowsHeight);
        frame.setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        frame.setVisible(true);

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

        JButton monkeyBtn = new JButton("<html>---monkey---<br>go get -u<br>bou.ke/monkey</html>");
        monkeyBtn.setFont(new Font(monkeyBtn.getFont().getName(), monkeyBtn.getFont().getStyle(), 20));
        monkeyBtn.addActionListener(e -> ex("go get -u go get bou.ke/monkey"));

        JButton goMicroBtn = new JButton("<html>---go-micro v2---<br>go get<br>github.com/micro/go-micro/v2</html>");
        goMicroBtn.setFont(new Font(goMicroBtn.getFont().getName(), goMicroBtn.getFont().getStyle(), 20));
        goMicroBtn.addActionListener(e -> ex("go get github.com/micro/go-micro/v2"));

        JButton microBtn = new JButton("<html>---micro---<br>go get<br>github.com/micro/micro/v2</html>");
        microBtn.setFont(new Font(microBtn.getFont().getName(), microBtn.getFont().getStyle(), 20));
        microBtn.addActionListener(e -> ex("go get github.com/micro/micro/v2"));

        JButton mysqlBtn = new JButton("<html>---mysql---<br>go get<br>gorm.io/driver/mysql</html>");
        mysqlBtn.setFont(new Font(mysqlBtn.getFont().getName(), mysqlBtn.getFont().getStyle(), 20));
        mysqlBtn.addActionListener(e -> ex("go get gorm.io/driver/mysql"));

        JButton tomlBtn = new JButton("<html>---toml---<br>go get<br>github.com/BurntSushi/toml</html>");
        tomlBtn.setFont(new Font(tomlBtn.getFont().getName(), tomlBtn.getFont().getStyle(), 20));
        tomlBtn.addActionListener(e -> ex("go get github.com/BurntSushi/toml"));

        JButton cryptoBtn = new JButton("<html>---crypto---<br>go get<br>golang.org/x/crypto</html>");
        cryptoBtn.setFont(new Font(cryptoBtn.getFont().getName(), cryptoBtn.getFont().getStyle(), 20));
        cryptoBtn.addActionListener(e -> ex("go get golang.org/x/crypto"));

        JButton jwtBtn = new JButton("<html>---jwt-go---<br>go get<br>github.com/dgrijalva/jwt-go</html>");
        jwtBtn.setFont(new Font(jwtBtn.getFont().getName(), jwtBtn.getFont().getStyle(), 20));
        jwtBtn.addActionListener(e -> ex("go get github.com/dgrijalva/jwt-go"));

        JButton tidyBtn = new JButton("<html>tidy</html>");
        tidyBtn.setFont(new Font(tidyBtn.getFont().getName(), tidyBtn.getFont().getStyle(), 20));
        tidyBtn.addActionListener(e -> ex("go mod tidy"));

        JButton sessionBtn = new JButton("<html>---session---<br>go get<br>github.com/gin-contrib/sessions</html>");
        sessionBtn.setFont(new Font(sessionBtn.getFont().getName(), sessionBtn.getFont().getStyle(), 20));
        sessionBtn.addActionListener(e -> ex("go get github.com/gin-contrib/sessions"));

        JButton hystrixBtn = new JButton("<html>---hystrix---<br>go get<br>github.com/afex/hystrix-go/hystrix</html>");
        hystrixBtn.setFont(new Font(hystrixBtn.getFont().getName(), hystrixBtn.getFont().getStyle(), 20));
        hystrixBtn.addActionListener(e -> ex("go get github.com/afex/hystrix-go/hystrix"));

        JButton amqpBtn = new JButton("<html>---amqp---<br>go get<br>github.com/streadway/amqp</html>");
        amqpBtn.setFont(new Font(amqpBtn.getFont().getName(), amqpBtn.getFont().getStyle(), 20));
        amqpBtn.addActionListener(e -> ex("go get github.com/streadway/amqp"));


        root.add(tidyBtn);
        root.add(ginBtn);
        root.add(gormBtn);
        root.add(testifyBtn);
        root.add(monkeyBtn);
        root.add(goMicroBtn);
        root.add(microBtn);
        root.add(mysqlBtn);
        root.add(tomlBtn);
        root.add(cryptoBtn);
        root.add(jwtBtn);
        root.add(sessionBtn);
        root.add(hystrixBtn);
        root.add(amqpBtn);

    }

    public static void ex(String cmd) {
        Runtime mt = Runtime.getRuntime();
        try {
            Process pro = mt.exec(cmd);
            InputStream inputStream = pro.getInputStream();
            String read = read(inputStream);
            if (read.length() == 0) {
                JOptionPane.showMessageDialog(frame, cmd + " ==> successfully");
            } else {
                JOptionPane.showMessageDialog(frame, read);
            }
            inputStream.close();
            pro.waitFor();
        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String read(InputStream in) throws IOException {
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

