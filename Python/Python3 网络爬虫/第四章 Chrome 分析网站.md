# Chrome 分析网站
## Chrome 开发工具
1. 浏览器是从事编程开发人员必备的开发工具，世界上五大主流浏览器分别是：IE、Opera、Google 。
2. Chrome、Safari 和 Firefox，其中 Chrome 和 Firefox 是编程开发人员的首选，主要是两者运行速度、扩展性和用户体验都符合开发人员所需。
3. 选择 Chrome 作为分析网站工具，因为其简洁、速度快(无论是启动速度、页面解析速度还是 JavaScript 执行速度)，对 HTML5 和 CSS 的支持都比较完善。
4. Chrome开发工具的界面共有 9 个标签页：Elements、Console、Sources、Network、Performance、Memory、Application、Security 和 Audits 。
5. Chrome开发工具以 Web 调试为主，如果用于爬虫分析，熟练掌握 Elements 和 Network 标签就能满足大部分的爬虫需求。

## Elements 标签
1. 在 Elements 标签中允许从浏览器的角度看页面，也就是说可以看到 Chrome 渲染页面所需要的 HTML、CSS 和 DOM(Document Object Model) 对象。此外，还可以编辑内容更改页面显示效果。

## Network 标签
1. 在 Network 标签中可以看到页面向服务器请求的信息、请求的大小以及加载请求花费的时间。从发起网页页面请求 Request 后分析 HTTP 请求得到各个请求信息(包括状态、类型、大小、所用时间、Request 和 Response 等)。
2. Network 标签主要包括以下 5 个区域：
  - Controls: 控制 Network 的外观和功能。
  - Filter: 控制 Request Table 具体显示哪些内容。
    - ALL: 返回当前页面全部加载的信息，就是一个网页全部所需要的代码、图片等请求。
    - XHR: 筛选 Ajax 的请求链接信息。
    - JS: 主要筛选 JavaScript 文件。
    - CSS: 主要是 CSS 样式内容。
    - Img: 是网页加载的图片，爬取图片的URL都可以在这里找到。
    - Media: 是网页加载的媒体文件，如 MP3、RMVB 等音频视频文件资源。
    - Doc: 是 HTML 文件，主要用于响应当前 URL 的网页内容。
  - Overview: 显示获取到请求的时间轴信息，主要是对每个请求信息在服务器的响应时间进行记录。
  - Requests Table: 按前后顺序显示所有捕捉的请求信息，单击请求信息可以查看详细信息。
  - Summary: 显示总的请求数、数据传输量、加载时间信息。

3. 每条请求信息划分为以下 5 个标签：
 - Headers: 该请求的 HTTP 头部信息。
 - Preview: 根据所选择的请求类型(JSON、图片、文本)显示相应预览。
 - Response: 显示 HTTP 的 Response 信息。
 - Cookies: 显示 HTTP 的 Request 和 Response 过程中的 Cookies 信息。
 - Timing: 显示请求在整个生命周期中各部分花费的时间。

4. Headers 标签分为以下 4 个部分：
  - Gerneral: 记录请求链接、请求方式和请求状态码。
  - Response Headers: 服务器端的响应头，其参数说明如下：
    - Cache-Control: 指定缓存机制，优先级大于 Last-Modified 。
    - Connection: 包含很多标签列表，其中最常见的是 Keep-Alive 和 Close ，分别用于向服务器请求保持 TCP 连接和断开 TCP 连接。
    - Content-Encoding: 服务器通过这个头告诉浏览器数据的压缩格式。
    - Content-Length: 服务器通过这个头告诉浏览器回送数据长度。
    - Content-Type: 服务器通过这个头告诉浏览器回送数据的类型。
    - Date: 当前时间值。
    - Keep-Alive: 在 Connection 为 Keep-Alive 时，该字段才有用，用力啊说明服务器估计保留连接的时间和允许后续几个请求复用这个保持着的连接。
    - Server: 服务器通过这个头告诉浏览器服务器的类型。
    - Vary: 明确告知缓存服务器按照 Accept-Encoding 字段的内容分别缓存不同的版本。
  - Request Headers: 用户的请求头。其参数说明如下：
    - Accept: 告诉服务器客户端支持的数据类型。
    - Accept-Encoding: 告诉服务器客户端支持的数据压缩格式。
    - Accept-Charser: 可接受的内容编码 UTF-8 。
    - Cache-Control: 缓存控制，服务器控制浏览器要不要缓存数据。
    - Connection: 处理完这次请求后，是断开连接还是保持连接。
    - Cookie: 客户可以通过 Cookie 向服务器发送数据，让服务器识别不同的客户端。
    - Host: 访问主机名
    - Referer: 包含一个 URL ，用户从该URL代表的页面出发访问当前请求的页面，当浏览器向 Web 服务器发送请求的时候，一般会带上 Referer ，告诉服务器请求是从哪个页面 URL 过来的，服务器借此可以获得一些信息用于处理。
    - User-Agent: 中文名为用户代理，简称 UA ，是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
  - Query String Parameters: 请求参数，主要是将参数按照一定的形式(Get和POST)传递给服务器，服务器通过接收其参数进行相应的响应，这是客户端和服务端进行数据交互的主要方式之一。
5. Headers 标签的内容看起来很多，但实际使用过程中，爬虫开发人员只需关心请求链接、请求方式、请求头和请求参数的内容即可。
6. Preview 和 Response 是服务器返回的结果，两者之间对不同类型的响应结果有不同的显示方式：
  - 如果返回的结果是图片，那么 Preview 表示可显示图片的内容，Response 表示无法显示。
  - 如果返回的是 HTML 或 JSON ，那么两者皆能显示，但在格式上可能存在细微差异。

## 本章小结
1. Chrome 开发者工具的主要作用是进行 Web 开发调试，对于爬虫开发人员来说，应该熟练掌握 Elements、Console 和 Network 。其中 Network 是核心部分。
2. 一般分析网站最主要的是找到数据的来源，确定数据来源就能确定数据生成的具体方法。总结归纳分析网站的步骤如下：
  - 找出数据来源，大部分数据来源于 Doc、XHR 和 JS 标签。
  - 找到数据所在的请求，分析其请求链接、请求方式和请求参数。
  - 查找并确定请求参数来源。


