package com.example.demo.core.exception;

/**
 * アプリケーション例外
 * 業務エラーを検知した場合にスローする。
 * 基本的には再処理を促すメッセージを通知する。
 */
public class AppException extends RuntimeException {
	
	/** メッセージID */
	private String messageId;
	/** フィールド */
	private String field;
	
	/**
	 * コンストラクタ
	 * @param field フィールド
	 * @param cause 例外
	 */
	public AppException(String messageId, String field, Throwable cause) {
		super(cause);
		this.messageId = messageId;
		this.field = field;
	}
	
	/**
	 * コンストラクタ
	 * @pa
	 */
}