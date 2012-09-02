/*
Navicat SQL Server Data Transfer

Source Server         : sqlserver
Source Server Version : 80000
Source Host           : 192.168.1.101:1433
Source Database       : db_spring
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 80000
File Encoding         : 65001

Date: 2012-09-03 02:14:11
*/


-- ----------------------------
-- Table structure for [dbo].[t_person]
-- ----------------------------
DROP TABLE [dbo].[t_person]
GO
CREATE TABLE [dbo].[t_person] (
[ID] nvarchar(32) NOT NULL ,
[userName] nvarchar(100) NULL ,
[age] int NULL 
)


GO

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO [dbo].[t_person] ([ID], [userName], [age]) VALUES (N'1', N'zhang', N'22');
GO
INSERT INTO [dbo].[t_person] ([ID], [userName], [age]) VALUES (N'2', N'fei', N'33');
GO

-- ----------------------------
-- Indexes structure for table t_person
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table [dbo].[t_person]
-- ----------------------------
ALTER TABLE [dbo].[t_person] ADD PRIMARY KEY ([ID])
GO
