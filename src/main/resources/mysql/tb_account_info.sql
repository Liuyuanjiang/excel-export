/*
Navicat MySQL Data Transfer

Source Server         : localhost@root
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : excel

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-07-10 09:37:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_account_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_account_info`;
CREATE TABLE `tb_account_info` (
  `uuid` varchar(50) NOT NULL DEFAULT '' COMMENT '唯一标识',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
  `QQ` varchar(50) DEFAULT NULL COMMENT 'QQ号',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `createDate` datetime DEFAULT NULL COMMENT '创建日期',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account_info
-- ----------------------------
INSERT INTO `tb_account_info` VALUES ('5e119462-dcfe-4b77-9c2a-5c5d4709cab3', '9TqVEG#jpY@)', '&U)g*vJzu', 'SB-级级全布院', '$dTEn@qq.com', '0', '0', '表布字为常表通基表通8汉', '2016-07-10 09:22:56', '基》全级一1表汉常公汉为个汉通表三字0其为中8其收分范通汉5用5基一 表级字字汉');
INSERT INTO `tb_account_info` VALUES ('c6441a1a-deb0-4796-8063-c61fcad2e9e8', '^VcsTE6H5gdC', '0gHOd#CYa', 'SB-，字基，一', '35thR@qq.com', '1', '0', '字表字收基 个规常为规级', '2016-07-10 09:22:56', '为级5字字1，中《为字字51常0的字级字用务31一1中3字为务一级汉表《中字，于');
INSERT INTO `tb_account_info` VALUES ('58357814-5d5d-465e-ad5d-17c72784ec05', 'QjSYeNJ3d4zg', '&b10Uv18l', 'SB-将公0级级', 'v#H0N@qq.com', '8', '4', '《于为汉布，字个为三字常', '2016-07-10 09:22:56', ' 》字3《个范常0级个公其》三汉大字字级字收，用范的规范，汉汉字，三8三范0三为');
INSERT INTO `tb_account_info` VALUES ('151fd61a-c118-4ed7-a59f-d2776a15f71e', 'j1u8iJTTFlwI', '1bxg3cOj&', 'SB-字于级字8', 'x8Zld@qq.com', '27', '18', '中0，一国5收1用字其,', '2016-07-10 09:22:56', '为用的《字 字基公收规字公于，级5字3一 个表收00字字收务级的5为083国中中');
INSERT INTO `tb_account_info` VALUES ('d24305e9-6a34-43a8-a4bd-4cd51ef31b68', 's#0fxrzTM2fR', 'EQUEj4B62', 'SB-个字字5中', '2%X08@qq.com', '64', '48', '国中字一5级国字收字88', '2016-07-10 09:22:56', '中字院收个布汉中 中的大字范通字国005级，5一用55表布0中字0汉其80大，收');
INSERT INTO `tb_account_info` VALUES ('175405ed-c056-4462-81f7-75dda2cfe7d1', 'sdMfcVWvbK*#', 'yyJ3c@GvE', 'SB-，全院的收', 'jYHwg@qq.com', '125', '100', '中常0国个级《范3为字8', '2016-07-10 09:22:57', '院分级 用汉字范字，0，5于规《表5的汉0收于字国字级5字收为用为字字表布其0全');
INSERT INTO `tb_account_info` VALUES ('f4837554-1b9f-4278-8218-99a16e593beb', '#9M!Gp5P9TWG', 'bRrtii58u', 'SB-国0将字用', '!e2Zv@qq.com', '216', '180', '字布0》字，院将0，字全', '2016-07-10 09:22:57', '字》0规的字0汉院其用用通汉院一常0为将布务《个范级字院》个公，表基中级汉一汉国');
INSERT INTO `tb_account_info` VALUES ('a95302b1-18a9-429b-8e3e-68d36033814c', 'qXmv)0BU8$NL', 'Zn&R72YLB', 'SB-》收中布0', '4UHg%@qq.com', '343', '294', '字将级基0，,字1将字级', '2016-07-10 09:22:57', '汉为字用规汉三务级字用级，《布收级收表级5字其字为中0字，字分字全全基字级字通的');
INSERT INTO `tb_account_info` VALUES ('31183313-2689-4a76-8c39-77080ee20567', 'K@t5JnpsNp5j', 'udo)cZ#0s', 'SB-汉,其将8', 'EiWLl@qq.com', '512', '448', '三字通，个通58于字 级', '2016-07-10 09:22:57', '为收字基级中为汉0院0一将大字一，级，字0为字常字字，院字其布其分8字字 国收三');
INSERT INTO `tb_account_info` VALUES ('4a8664a4-ff63-4b01-b856-7024c64207da', 'ycx!wz*Hs^SY', 'SVInXzDXe', 'SB-，级字 字', '(mWK1@qq.com', '729', '648', '全三，规，字规,为通一0', '2016-07-10 09:22:57', '字5布公表分为中公的字8范，为字 三用全5，大字表的务一大为布中中的5级5，，字');
INSERT INTO `tb_account_info` VALUES ('f239ecfb-65f2-402e-a56e-0b42f2d7dac2', '0J1M@yfAjBVF', '1*rpW*$uU', 'SB-表常，务字', 'ZVW!a@qq.com', '1000', '900', '》，5一收的  通5将字', '2016-07-10 09:22:57', '0字字基级级表级汉级国3，5汉，表用汉3规5 用级汉为基，级汉收常字布5常表公分');
INSERT INTO `tb_account_info` VALUES ('32be004b-a7af-4a2d-a3d5-1af38f4c9acf', 'H$O(DFJlHyL&', 'pnVOA@*eD', 'SB-汉布，常字', 'J7Qt9@qq.com', '1331', '1210', '大基大分规个规三5大全5', '2016-07-10 09:22:57', '国全级汉01用，基中常一,字基级表其字收规三0布0公《 中5字院《8汉0级00,');
INSERT INTO `tb_account_info` VALUES ('af33ee84-dad4-4c12-9ac7-2658f2c450b5', 'Vplr12@T2Y0T', '70mYD%#!7', 'SB-用级三字收', 'zBacf@qq.com', '1728', '1584', '字5中一0》常常字国为字', '2016-07-10 09:22:57', '分字0级为公1公将8字规8字《用务分0常0字其常院布将,级 的8收其务布字一全中');
INSERT INTO `tb_account_info` VALUES ('c4eb8db9-3591-4639-a9f2-eb582fdaf0a3', 'hAPJ&I1amp#g', 'N9)L6$Bg)', 'SB-5，，,的', '&oP4z@qq.com', '2197', '2028', '表国全汉用其字》，字5大', '2016-07-10 09:22:57', '将0字级的将院汉院，分，《基字，0公用的表,0其字80汉3字字汉级35表表，字《');
INSERT INTO `tb_account_info` VALUES ('f338527f-0a60-4392-b9e4-59c875204162', '0pX8acuk1u1K', 'JCt$3cWR1', 'SB-15》,一', 'gCPgv@qq.com', '2744', '2548', '30字35《分公一收分字', '2016-07-10 09:22:57', '用字字5常大基通常字中，于级收字字于》表，1为汉大5范布005，三其级汉《国汉将');
INSERT INTO `tb_account_info` VALUES ('0e17ffad-3e17-4806-948e-dca676be57d6', 'CuJWShA$URys', 'MdG6S)bpa', 'SB-字级08布', 'lzQru@qq.com', '3375', '3150', ' 0国字级1的基1基8个', '2016-07-10 09:22:57', '通的中，个,字一的级规字全5 0国3 级字国，为字3表通常字51字分汉一字》字分');
INSERT INTO `tb_account_info` VALUES ('087d6cd9-89d3-4fce-8865-48e6496cf799', '2dBgH3#JC%nB', 'H1wZHqBNW', 'SB-公级中80', 'ZKI(X@qq.com', '4096', '3840', '为个8,一字院收3用大公', '2016-07-10 09:22:57', '的通布基公为规个《公规级用为字收汉1公个布院表级 务表务字院表院的级8其一5用为');
INSERT INTO `tb_account_info` VALUES ('3999e74d-1617-44d4-a7d4-7e53b62014b3', 'f6JqaW@l27du', 'z7hxN2iJg', 'SB-《8分 规', '!mjck@qq.com', '4913', '4624', '0字基3表级将字一,0，', '2016-07-10 09:22:57', '一字3，级字为5字中0布汉字为1公基1规字5级个用院3中收分级,，5用中级05布');
INSERT INTO `tb_account_info` VALUES ('c713df2b-9f5d-4cf8-ab04-c0a493d54573', '1KInPWj6vtOg', 'X5LWw9kGd', 'SB-字为一将规', 'jzfnZ@qq.com', '5832', '5508', '3级全0于通布范为用分务', '2016-07-10 09:22:57', '为级级院分字表常 字收0国于字其于汉级院中字通为一将用 用》收大 ，《字503中');
INSERT INTO `tb_account_info` VALUES ('0d65a340-8308-4538-a09d-7b9b97b85006', 'Pi3UjEtn&NJa', 'DN$oG@)kf', 'SB-中国8三字', 'EANuw@qq.com', '6859', '6498', '的三0字的汉用》 ，字汉', '2016-07-10 09:22:57', '布的表三将表,,公，于公3表一0用字35于国字布用字表用表于，1汉为国0通字的三');
