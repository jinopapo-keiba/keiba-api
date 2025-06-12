# 概要
競馬の情報を取得するapi

# パッケージ構造
## config
applicationの設定クラスの定義場所
## controller
エントリーポイントとなるクラスの定義場所
## controller/coverter
他のパッケージのクラスをcontoller内のクラスに変換するクラスの定義場所
## controller/intercepter
aop的にcontrollerに挟みたいクラスの定義場所
## controller/request
apiのリクエストパラメータのクラスの定義場所
## controller/response
apiのレスポンスクラスの定義場所
## converter
entityパッケージ内のクラスに変換するクラスの定義場所
## entity
ドメインとなるクラスの定義場所
## repository
dbからデータを取得するためのインターフェースの定義場所
実際のクエリ等はresource/com...配下にある
## service
メインロジックのクラスの定義場所
## utils
便脈によらない便利クラスの定義場所
## valueobject
特別な値を定義したいときのクラスの定義場所

# 言語
java17

# fw
pom.xmlを参照