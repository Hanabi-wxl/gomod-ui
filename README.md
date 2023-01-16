# gomod-ui
A swing-based UI program to simplify the writing of go get

#### 第一部分 设置窗口的显示
使用了最简单的grid布局，可自由定义组件显示几行几列
``` java
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
```
#### 第二部分 添加按钮
每一条go get指令都简化为点击按钮实现
``` java
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
```

#### 第三部分 事件函数
``` java
// 执行传入的go get指令
public void ex(String cmd) {
    Runtime mt = Runtime.getRuntime();
    try {
        Process pro = mt.exec(cmd);
        InputStream inputStream = pro.getInputStream();
        // 读取输入内容
        String read = read(inputStream);
        // 若无内容则显示默认对话框
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

// 读取输入
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
```

#### 使用方法
最原始的执行方法：将java代码编译生成的class文件放于go项目根路径（与go.mod文件同级）再执行java Main运行，若窗口空白请微调一下窗口尺寸即可显示。
