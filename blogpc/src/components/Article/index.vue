<template>
  <div class="article">
    <div class="article__header">
      <a href="##" class="article__title">服务端升级 Ubuntu 20.04 LTS 记录</a>
      <div class="article__time">
        <span><a-icon type="history" /> 2020-05-03 20:14</span>
      </div>
      <div class="article__">
      </div>
    </div>
    <div class="article__content">
      本文将介绍如何在当前时间点，将服务器版本的 Ubuntu 18.04 LTS 升级为最新的 Ubuntu 20.04 LTS，以及升级过程中的一些细节，希望能帮到跃跃欲试的 Ubuntu 同好。

将数据进行备份等操作需要自行处理，另外确保网络稳定，建议都在服务器跳板机上进行操作，更为稳妥。

当前这篇内容已经运行在 Ubuntu 20.04 LTS 系统环境中，:)
写一篇稍长一些的总结，记录一下最近的时光。这篇文章原本应该在前几个周末写出，但是最近实在太忙了，忙到这个月都快结束了，才想起来还没有做总结梳理。今天写完手头的活，看到家里机柜 UPS 负载为零，猛的记起好像之前在给 UPS 换电池之后就忘记了把设备重新插回去。在将设备断电接入 UPS 前看到设备运行时间在 150 ～ 170 天，
    </div>
    <div class="article__tags">
      <a-tag color="pink" class="article__tag">Vue</a-tag>
      <a-tag color="blue" class="article__tag">Vue router</a-tag>
      <a-tag color="orange" class="article__tag">Vuex</a-tag>
    </div>
  </div>
</template>

<script>
export default {
  name: 'zx-article'
}
</script>

<style scoped>
  .article{
    padding: 10px;
    background-color: #fff;
    border: 1px solid #eee;
    border-radius: 4px;
  }
  .article__header{
    font-size: 18px;
    padding: 5px 0;
  }
  .article__title{
    color: #1890ff;
    margin-bottom: 8px;
  }
  .article__tags{
    margin-top: 12px;
  }
  .article__tag{
    cursor: pointer;
    opacity: .85;
  }
  .article__tag:hover{
    opacity: 1;
  }
  .article__time{
    font-size: 12px;
    color: rgba(0, 0, 0, .65);
  }
</style>
