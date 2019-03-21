-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 21, 2019 at 08:40 AM
-- Server version: 10.3.13-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id8606975_acgassistant`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `admID` int(11) NOT NULL,
  `admName` varchar(10) CHARACTER SET utf8 NOT NULL,
  `admPwd` varchar(20) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`admID`, `admName`, `admPwd`) VALUES
(1, 'adm01', 'adm123456'),
(2, 'adm02', '22222'),
(3, 'adm03', 'qwerty'),
(5, 'adm04', '44444'),
(6, 'adm05', '55555'),
(7, 'adm06', '123456'),
(9, 'adm07', '77777'),
(14, 'adm08', '88888');

-- --------------------------------------------------------

--
-- Table structure for table `animation`
--

CREATE TABLE `animation` (
  `aniID` int(11) NOT NULL COMMENT '編號',
  `aniType` varchar(10) NOT NULL DEFAULT 'ani' COMMENT '類別(10)',
  `aniName` varchar(50) NOT NULL COMMENT '動畫名稱(50)',
  `aniPic` varchar(50) NOT NULL COMMENT '預覽圖(50)',
  `aniStartDate` varchar(20) NOT NULL COMMENT '起播日',
  `aniEndDate` varchar(20) NOT NULL COMMENT '結束日',
  `aniUplaodDay` varchar(5) NOT NULL COMMENT '星期幾更新(5)',
  `aniTrailer` varchar(100) NOT NULL COMMENT '預告網址(100)',
  `aniInfo` varchar(1000) NOT NULL COMMENT '動畫介紹(1000)'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `animation`
--

INSERT INTO `animation` (`aniID`, `aniType`, `aniName`, `aniPic`, `aniStartDate`, `aniEndDate`, `aniUplaodDay`, `aniTrailer`, `aniInfo`) VALUES
(1, 'ani', '七龍珠', 'ani01', '2019/1/20', '2019/4/7', '7', 'ojtb9ga-nyo', '間起北家。許士業小健明他該有金，升隊民國團，理提已？經局由情色於！的經舉國有發為題他什常直流望字防多學。'),
(2, 'ani', '海賊王', 'ani02', '2019/1/10', '2019/3/14', '7', '7S3UV4Goy2w', '講來破會全聞室；個這提金自辦太完作，明然內然我山是？'),
(3, 'ani', '火影忍者', 'ani03', '2019/1/12', '2019/3/14', '4', 'GEJebBjtwzI', '力過究可象定開不展法南顧取什說；候社進。動動裡上謝再苦，務的裡度成的文電兒說流像狀好音！得年信設電續車白我。'),
(4, 'ani', '蠟筆小新', 'ani04', '2019/1/21', '2019/4/1', '1', 'PfurMZdm9JM', '呢適不最大情不總子見著的知世統，於型各，念得一子北素、月可媽目職朋二書，話算數任，就想的不般充？'),
(5, 'ani', '我們這一家', 'ani05', '2019/1/23', '2019/4/17', '2', 'sDapgipGQQY', '指委美看洲，年絕沒機大成期強構次期事動所人黑否醫八我用問和能小，離麼不說他明們性國成家色藝的！'),
(6, 'ani', '獵人', 'ani06', '2019/1/24', '2019/4/8', '4', '1kFf9NvpLHM', '結晚福她皮。原以是說改候已屋住他沒學要夠心於他苦包竟歡提！'),
(7, 'ani', '棋靈王', 'ani07', '2019/1/27', '2019/4/9', '3', 'H-vYfpSRByc', '門境一知土可個時平意舉中道爸富開手作清，民必日詩是刻車是史現急預畫大……把然山於覺間是看配長機程民到們讀。'),
(8, 'ani', '刀劍神域', 'ani08', '2019/1/2', '2019/4/9', '6', '4GQFjoyl6_s', '一再在立今麼關決！起現顯三算人到而其夠法子有上行，藝仍生苦！我況市給能樂戲成華港卻的。'),
(9, 'ani', '進擊的巨人', 'ani09', '2019/2/20', '2019/3/7', '5', 'pGmsnULNO_0', '稱野有了小況行家我做，寫身為市乎紙我我一大大：活樣裡與來農時作定很減裡這景樓火遠的愛間。'),
(10, 'ani', '機動戰士鋼彈', 'ani10', '2019/3/20', '2019/4/14', '1', 'ldvurDVXKyo', '教熱文個多？一林黃所叫可得一英所：一只千反後我說易，收運股；以又得平。一個百引國在檢因，助等麼香團可部開情我最近放本母亞錢較國理。');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `cmtID` int(11) NOT NULL,
  `cmtMemID` int(11) NOT NULL,
  `cmtNews` int(11) NOT NULL,
  `cmtInfo` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`cmtID`, `cmtMemID`, `cmtNews`, `cmtInfo`) VALUES
(1, 0, 0, '00'),
(2, 0, 0, '00'),
(3, 0, 0, '00'),
(4, 0, 0, '00'),
(7, 0, 0, '11'),
(8, 10, 22, '你好'),
(9, 10, 22, 'qefqefqefqef');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `eveID` int(11) NOT NULL,
  `eveType` varchar(50) NOT NULL,
  `eveSubType` varchar(10) NOT NULL,
  `eveLocationType` varchar(10) NOT NULL,
  `eveName` varchar(50) NOT NULL,
  `evePic` varchar(50) NOT NULL,
  `eveStartDate` date NOT NULL,
  `eveEndDate` date NOT NULL,
  `evelocation` varchar(50) NOT NULL,
  `eveWeb` varchar(100) NOT NULL,
  `eveMap` varchar(100) NOT NULL,
  `eveInfo` varchar(1000) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`eveID`, `eveType`, `eveSubType`, `eveLocationType`, `eveName`, `evePic`, `eveStartDate`, `eveEndDate`, `evelocation`, `eveWeb`, `eveMap`, `eveInfo`) VALUES
(1, 'eve', 'O', 'N', '國際動漫節', 'eve01', '2019-03-12', '2019-03-19', '台北市', 'http://www.ccpa.org.tw', '25.0566068,121.6159273', '國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節國際動漫節'),
(2, 'eve', 'O', 'C', 'FF活動', 'eve02', '2019-03-02', '2019-03-03', '台北小巨蛋', 'http://www.f-2.com.tw', '25.0513896,121.5475527', 'FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動FF活動'),
(3, 'eve', 'F', 'N', '台北國際書展', 'eve03', '2019-02-13', '2019-02-17', '台北世貿中心', 'http://www.tibe.org.tw/', '25.0332639,121.5601353', '台北國際書展台北國際書展台北國際書展台北國際書展台北國際書展台北國際書展台北國際書展台北國際書展台北國際書展台北國際書展'),
(4, 'eve', 'F', 'N', '陽明山花季', 'eve04', '2019-02-15', '2019-03-17', '陽明山', 'https://www.facebook.com/ymsflower/', '25.1748348,121.5362591', '陽明山花季陽明山花季陽明山花季陽明山花季陽明山花季陽明山花季陽明山花季陽明山花季陽明山花季陽明山花季'),
(5, 'eve', 'F', 'C', '金門國際馬拉松', 'eve05', '2019-02-16', '2019-02-17', '金門大學', 'https://www.sportsnet.org.tw/20190217_web/', '24.44021,118.3198785', '金門國際馬拉松金門國際馬拉松金門國際馬拉松金門國際馬拉松金門國際馬拉松金門國際馬拉松金門國際馬拉松金門國際馬拉松金門國際馬拉松'),
(6, 'eve', 'O', 'N', '台灣國際藝術節', 'eve06', '2019-02-16', '2019-04-21', '國家戲劇院', 'http://tifa.npac-ntch.org/2019/tw/', '25.0361321,121.5165283', '台灣國際藝術節台灣國際藝術節台灣國際藝術節台灣國際藝術節台灣國際藝術節台灣國際藝術節台灣國際藝術節台灣國際藝術節'),
(7, 'eve', 'F', 'N', '野柳石光-夜訪女王', 'eve07', '2019-03-01', '2019-04-30', '野柳地質公園', 'http://www.ylgeopark.org.tw/content/index/index.aspx', '25.2096368,121.6840113', '野柳石光-夜訪女王野柳石光-夜訪女王野柳石光-夜訪女王野柳石光-夜訪女王野柳石光-夜訪女王野柳石光-夜訪女王野柳石光-夜訪女王野柳石光-夜訪女王'),
(8, 'eve', 'O', 'S', '臺灣國際蘭展', 'eve08', '2019-03-02', '2019-03-11', '蘭花生物科技園區', 'http://www.tiostw.com/', '23.3397485,120.3760203', '臺灣國際蘭展臺灣國際蘭展臺灣國際蘭展臺灣國際蘭展臺灣國際蘭展臺灣國際蘭展臺灣國際蘭展臺灣國際蘭展'),
(9, 'eve', 'O', 'N', '觀音觀鷹', 'eve09', '2019-04-01', '2019-05-31', '和平島公園', 'https://www.northguan-nsa.gov.tw/user/main.aspx?Lang=1', '25.1606293,121.7616586', '觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹觀音觀鷹'),
(10, 'eve', 'O', 'N', 'TAIPEI 101 國際登高賽', 'eve10', '2019-05-04', '2019-05-04', '101大樓', 'https://www.taipei-101.com.tw/tw/index.aspx', '25.0339639,121.5622835', 'TAIPEI 101 國際登高賽TAIPEI 101 國際登高賽TAIPEI 101 國際登高賽TAIPEI 101 國際登高賽TAIPEI 101 國際登高賽');

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE `follow` (
  `floID` int(11) NOT NULL,
  `floMemID` int(11) NOT NULL,
  `floItemID` int(11) NOT NULL,
  `floType` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `follow`
--

INSERT INTO `follow` (`floID`, `floMemID`, `floItemID`, `floType`) VALUES
(5, 0, 0, '00'),
(4, 0, 0, '11');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `memID` int(11) NOT NULL,
  `memName` varchar(10) NOT NULL,
  `memPwd` varchar(20) NOT NULL,
  `memEmail` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`memID`, `memName`, `memPwd`, `memEmail`) VALUES
(1, 'member01', 'aaaaa', 'aaaaa@gmail.com'),
(2, 'member02', 'bbbbb', 'bbbbb@gmail.com'),
(3, 'member03', 'ccccc', 'ccccc@gmail.com'),
(4, 'member04', 'ddd', 'ddddd@gmail.com'),
(5, 'member05', 'eeeee', 'eeeee@gmail.com'),
(6, 'member06', 'fffff', 'fffff@gmail.com'),
(7, 'member07', 'gg', 'ggggg@gmail.com'),
(8, 'member08', 'hhhhh', 'hhhhh@gmail.com'),
(9, 'member09', 'iiii', 'iiii@gmail.com'),
(10, 'member10', 'jjj', 'jj@gmail.com'),
(44, 'qwertyuiop', 'qwertyuiop', 'qwertyuiop');

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE `news` (
  `nwsID` int(11) NOT NULL,
  `nwsType` varchar(10) NOT NULL,
  `nwsName` varchar(50) NOT NULL,
  `nwsPic` varchar(50) NOT NULL,
  `nwsInfo` varchar(1000) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `news`
--

INSERT INTO `news` (`nwsID`, `nwsType`, `nwsName`, `nwsPic`, `nwsInfo`) VALUES
(22, 'nws', '2019冬番情報', 'nws01', '月這金我建子系形書如法究要到去臺無想把女節實也；是你星。苦吸整一院，牛長領慢、界女表官可見本子界發個教爾張一。神傷人壓面多告！卻今有師港年媽發通你雄車出麼學我情然價西里反資之影點物料好是雜智錯，語聲帶假！來商了都和水去登及圖模香問一太商應的充裡運管以衣！'),
(23, 'nws', '海盜王作者採訪', 'nws02', '路溫給生葉他比安她那然養防，易清會，不使然嗎這中非：爸小出？水食者！北裡合樂送沒開許利在說名直的當乎服名臺術之習車際；樹世古，母大否臺的通錢作情。於統的；可書試三規些！'),
(24, 'nws', '任地獄終於要釋出元老遊戲-超級瑪利亞於他平台推出', 'nws03', '月這金我建子系形書如法究要到去臺無想把女節實也；是你星。苦吸整一院，牛長領慢、界女表官可見本子界發個教爾張一。神傷人壓面多告！卻今有師港年媽發通你雄車出麼學我情然價西里反資之影點物料好是雜智錯，語聲帶假！來商了都和水去登及圖模香問一太商應的充裡運管以衣！'),
(25, 'nws', '人死後是否會到異世界？英國研究給予合理依據！', 'nws04', '臺低來個車人的學近，舉知法天，報個了們的學想動會響，種興了就皮想國比？清會心視女防分：今養期了當企不上己特程為的廠客。因然分這是實聽對們年語別一是員識和人類不一張臺畫：死門國是好著推獲天銷的兩從何球道張不卻臺乎體是清黑眼親學見。'),
(26, 'nws', '慧慧終於學會了新魔法，作者於小說透露端倪', 'nws05', '包自媽頭力條一界假中叫，跑亞我太企男同實麼，在創式用綠麼自背可頭隊實看中主，然看連很至熱投市？福年夫事童另，隨力言民業速很目出臺大，備人現事，英本別大外師道小國史，動態天人驚線不十第賽正福頭場書從界造新，學國見把達工了雨。我年成門止是況大注好長只後花小供不們。'),
(27, 'nws', '台灣最大同人活動居然要在這裡展出！網友直呼：難以置信', 'nws06', '回拿示經教條水在創了對，令的自親視故兒中帶：會油感清識家外利同在明，得如為大合生多；收灣創子結教展實國地行模實能影聲、還作。'),
(28, 'nws', '夏季新番排名調查，這部居然成為最大黑馬！', 'nws07', '一品藥說為得名的，這住算羅白基過園動企面手長知、家海視東以坡出後、小了心！合家愛指時是，國定成心無了支始便業天哥出方……子的謝參。特時教望一身之南意感裡較氣廣下半；被你平特文思且養意太城招場食十的雄體的場元，修舞取用內許型星？'),
(29, 'nws', '傳說級動畫閃電屁屁車即將推出電影版，於明年三月推出', 'nws08', '早理查集人表辦作小得知在養入同於統不或民單中待發想境力，路中觀：來外打地魚來即出，點業不委得質千們人家地目還優說原水照！'),
(30, 'nws', '六等分的新郎即將動畫化，女網友興奮直呼:我全都要!', 'nws09', '邊一會民日工放密愛統共一。性分一有物裡國近了你解登了，些事件服機就告許團就意：低還找屋情歌配人、童回要因手母，科一一問背名這方作。生成專表從論可境往的！人開只明示定創的同內受！'),
(31, 'nws', '人氣網路遊戲「白色沙灘」將推出新職業寄居蟹戰士，帶你直接看第一手消息', 'nws10', '十說統別說留從了最夠中好果。不之指路、人一高對權立水力常記雲看你地意樹者、較多書問爸大部留……仍美究自望們臺的阿的顧，雜計本滿地情館快到球離化過白不金連氣持？');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`admID`);

--
-- Indexes for table `animation`
--
ALTER TABLE `animation`
  ADD PRIMARY KEY (`aniID`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`cmtID`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`eveID`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`floID`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`memID`);

--
-- Indexes for table `news`
--
ALTER TABLE `news`
  ADD PRIMARY KEY (`nwsID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrator`
--
ALTER TABLE `administrator`
  MODIFY `admID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `animation`
--
ALTER TABLE `animation`
  MODIFY `aniID` int(11) NOT NULL AUTO_INCREMENT COMMENT '編號', AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `cmtID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `eveID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `follow`
--
ALTER TABLE `follow`
  MODIFY `floID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `memID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `news`
--
ALTER TABLE `news`
  MODIFY `nwsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
