# コーディング試験をClaude Codeだけでやってみた

## 概要
下記のエンドポイントを持つ、書籍管理 API サーバーを実装する課題がある。
- GET /books -> 全ての書籍情報の一覧を返します
- POST /books -> 指定した書籍情報を登録します

これらを実現する実装コードをClaude Codeだけで記述してみる。
設計や仕様については手を加えることになるが、コーディングだけは一切、手を加えないことを条件とする。

前提条件を以下に述べる。
- 必要な API はわかっており、それは2つある
- テーブルのスキーマは既に用意されている
- DB の選定は PostgresSQL であると既に決まっている
- API サーバーと DB はDockerコンテナをローカルで起動する