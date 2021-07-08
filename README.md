# booku
 
Demo: http://booku-env-1.eba-ut6fymmh.us-east-2.elasticbeanstalk.com/books

- 背表紙を表示するWeb本棚の作成を目指したWebアプリケーション
- Kotlin(Spring Framework, thymeleaf)

- 現在の機能
  - 本棚へ本を追加(手入力)
  - 本棚へ本を追加(ISBNで自動入力, OpenBD API)
  - 本棚の一覧表示(テキスト)
  - 本棚の本の編集

- 今後実装予定の機能
  - ソート機能の追加 
  - 本の情報の項目にサイズの追加
  - ISBN入力で本の立体サイズの計算
  - 背表紙画像の作成(おそらくサイズに合わせた文字列?)
  - 貪欲法による本棚構築・画像作成
  - データベースの利用
  - ユーザ認証 -> 個人の本棚に変更
