package com.hello780831.restaurant.appDefine

import com.hello780831.restaurant.model.Restaurant

enum class DefaultRestaurant(
    private val area :String,
    private val restaurantName: String,
    private val phone :String,
    private val address: String,
    private val googleRatio :Float,
    private val hasPark :Boolean,
    private val price :String,
    private val mapLink :String
) {
    A("宜蘭市","地6攤牛排 城東店","0930059471","宜蘭縣宜蘭市舊城東路9號",4.0f,true,"中","https://goo.gl/maps/AqrrdWt8VoUNBn636"),
    B("宜蘭市","喬培(宜大店)義大利麵·輕食·焗烤·燉飯","039315252","宜蘭縣宜蘭市擺厘路9之75號",3.9f,true,"中","https://goo.gl/maps/L87ZMq2NtN5NHF4S7"),
    C("宜蘭市","富美海鮮火鍋 宜蘭店","039313807","宜蘭縣宜蘭市宜興路一段48號",4.4f,true,"高","https://goo.gl/maps/F9frgfbFqTLUYvaa7"),
    D("宜蘭市","山之水叉燒肉焿","0922257175","宜蘭縣宜蘭市文昌路9號260",4.2f,true,"低","https://goo.gl/maps/AUF3ukkfWS1gGW7r9"),
    E("宜蘭市","十六崁瓜仔雞麵","039364797","宜蘭縣宜蘭市中山路三段154號",4.1f,true,"低","https://goo.gl/maps/iE7oCKSEgQFBUW7f8"),
    F("宜蘭市","眷村味小吃","039331350","宜蘭縣宜蘭市舊城北路150號號 一 樓",4.4f,true,"低","https://goo.gl/maps/DByTLyYVUjimQufp9"),
    G("宜蘭市","金澤魯肉飯","039358000","宜蘭縣宜蘭市西後街140號",4.2f,false,"中","https://goo.gl/maps/fMmFoiMG81qm3aQV7"),
    H("宜蘭市","深山牧場壽喜燒","039310310","宜蘭縣宜蘭市弘志路69號",4.0f,false,"高","https://goo.gl/maps/H2sE8H3DY6Mxo26YA"),
    I("宜蘭市","久千代海鮮百匯餐廳","039334000","宜蘭縣宜蘭市復興路二段168號",3.7f,true,"高","https://goo.gl/maps/X5t9rq282B7J23eb8"),
    J("宜蘭市","粵園港式燒腊","039329345","260宜蘭縣宜蘭市文化路22號",3.9f,true,"中","https://goo.gl/maps/dywjXActnh6yFVyq8"),


    K("礁溪鄉","老先覺麻辣窯燒鍋","039875686","宜蘭縣礁溪鄉溫泉路15號",3.5f,true,"中","https://goo.gl/maps/oWGfpJYwHcPS9XRG6"),
    L("礁溪鄉","礁溪ㄔˇ留香 (現做現蒸)湯包專賣","039882846","宜蘭縣礁溪鄉中山路二段154號",3.7f,true,"低","https://goo.gl/maps/otwEe8eShhcdVpqr7"),
    M("礁溪鄉","三妹爌肉飯","0918889252","宜蘭縣礁溪鄉中山路二段134號",4.0f,true,"低","https://goo.gl/maps/F9frgfbFqTLUYvaa7"),
    N("礁溪鄉","來益漁行(海燒)大溪現撈/礁溪美食","0937871115","宜蘭縣礁溪鄉中山路二段106號",4.5f,true,"中","https://g.page/JiaoxiHaishiao?share"),
    O("礁溪鄉","礁溪鐘氏肉羹","039882853","宜蘭縣礁溪鄉中山路二段19號",4.1f,true,"低","https://goo.gl/maps/BeqBrFYhKZhYKTiy6"),
    P("礁溪鄉","火車頭小吃","039889989","宜蘭縣礁溪鄉中山路二段16號",3.8f,true,"低","https://goo.gl/maps/3ntztB44FDemz9m46"),
    Q("礁溪鄉","玉仁八寶冬粉 1號店","039881232","宜蘭縣礁溪鄉中山路一段326號",4.5f,true,"中","https://goo.gl/maps/UZ2y4voQnJkQKGcX7"),
    R("礁溪鄉","林家豬腸冬粉","039882030","宜蘭縣礁溪鄉中山路一段276號",4.2f,true,"低","https://goo.gl/maps/oTEh9CmjjbQeK55KA"),
    S("礁溪鄉","鬼椒一番鍋 礁溪店","039886689","宜蘭縣礁溪鄉和平路122號",3.9f,true,"高","https://goo.gl/maps/ikjxjsgacsvEMTru6"),
    T("礁溪鄉","上乘三家涮涮鍋 礁溪店","039888519","宜蘭縣礁溪鄉中山路二段171之1號",4.4f,true,"高","https://g.page/b3house02?share");

    fun toRestaurant() :Restaurant{
        return Restaurant(area, restaurantName, phone, address, googleRatio, hasPark, price, mapLink)
    }
}


//礁溪



//1	老先覺麻辣窯燒鍋	039875686	宜蘭縣礁溪鄉溫泉路15號	3.5	是	中	https://goo.gl/maps/oWGfpJYwHcPS9XRG6
//2	礁溪ㄔˇ留香 (現做現蒸)湯包專賣	039882846	宜蘭縣礁溪鄉中山路二段154號	3.7	是	低	https://goo.gl/maps/otwEe8eShhcdVpqr7
//3	三妹爌肉飯	0918889252	宜蘭縣礁溪鄉中山路二段134號	4.0	是	低	https://goo.gl/maps/1NjZznLo2XteeNxY7
//4	來益漁行(海燒)大溪現撈/礁溪美食	0937871115	宜蘭縣礁溪鄉中山路二段106號	4.5	是	中	https://g.page/JiaoxiHaishiao?share
//5	礁溪鐘氏肉羹	039882853	宜蘭縣礁溪鄉中山路二段19號	4.1	是	低	https://goo.gl/maps/BeqBrFYhKZhYKTiy6
//6	火車頭小吃	039889989	宜蘭縣礁溪鄉中山路二段16號	3.8	是	低	https://goo.gl/maps/3ntztB44FDemz9m46
//7	玉仁八寶冬粉 1號店	039881232	宜蘭縣礁溪鄉中山路一段326號	4.5	是	中	https://goo.gl/maps/UZ2y4voQnJkQKGcX7
//8	林家豬腸冬粉	039882030	宜蘭縣礁溪鄉中山路一段276號	4.2	是	低	https://goo.gl/maps/oTEh9CmjjbQeK55KA
//9	鬼椒一番鍋 礁溪店	039886689	宜蘭縣礁溪鄉和平路122號	3.9	是	高	https://goo.gl/maps/ikjxjsgacsvEMTru6
//10	上乘三家涮涮鍋 礁溪店	039888519	宜蘭縣礁溪鄉中山路二段171之1號	4.4	是	高	https://g.page/b3house02?share

//宜蘭縣
//1	地6攤牛排 城東店	0930059471	宜蘭縣宜蘭市舊城東路9號	4.0	是	中	https://goo.gl/maps/AqrrdWt8VoUNBn636
//2	喬培(宜大店)義大利麵·輕食·焗烤·燉飯	039315252	宜蘭縣宜蘭市擺厘路9之75號	3.9	是	中	https://goo.gl/maps/L87ZMq2NtN5NHF4S7
//3	富美海鮮火鍋 宜蘭店	039313807	宜蘭縣宜蘭市宜興路一段48號	4.4	是	高	https://goo.gl/maps/F9frgfbFqTLUYvaa7
//4	山之水叉燒肉焿	0922257175	宜蘭縣宜蘭市文昌路9號260	4.2	是	低	https://goo.gl/maps/AUF3ukkfWS1gGW7r9
//5	十六崁瓜仔雞麵	039364797	宜蘭縣宜蘭市中山路三段154號	4.1	是	低	https://goo.gl/maps/iE7oCKSEgQFBUW7f8
//6	眷村味小吃	039331350	宜蘭縣宜蘭市舊城北路150號號 一 樓	4.4	否	低	https://goo.gl/maps/DByTLyYVUjimQufp9
//7	金澤魯肉飯	039358000	宜蘭縣宜蘭市西後街140號	4.2	否	中	https://goo.gl/maps/fMmFoiMG81qm3aQV7
//8	深山牧場壽喜燒	039310310	宜蘭縣宜蘭市弘志路69號	4.0	否	高	https://goo.gl/maps/H2sE8H3DY6Mxo26YA
//9	久千代海鮮百匯餐廳	039334000	宜蘭縣宜蘭市復興路二段168號	3.7	是	高	https://goo.gl/maps/X5t9rq282B7J23eb8
//10	粵園港式燒腊	039329345	260宜蘭縣宜蘭市文化路22號	3.9	是	中	https://goo.gl/maps/dywjXActnh6yFVyq8