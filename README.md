# bukkit-FreeKeepinventory
A simple bukkit inventory keeping plugin. - 一个简单的死亡不掉落插件

Command:

```
/fkp //Check the state of keeping inventory. - 检查死亡不掉落的状态

/fkp [true/false] //Enable/Disable keepinventory. - 启用/禁用 死亡不掉落

```

config.yml:

```
keepinventory:

  defualt-enable: true #If true,plugin will set player's keepinventory to true when they first join. - 是否自动设置死亡不掉落
  
  keepitem: true #保留物品
  
  keepexp: true #保留经验
  
  blacklist-worlds: #Keepinventory will be disable when player in a wolrd in the list. 死亡不掉落将在列表中的世界里禁用
  
default-language: 'zh_cn'
```
