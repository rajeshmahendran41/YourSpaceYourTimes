package com.aspect;

import java.sql.Timestamp;
/*     */ 
import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AspectException
/*     */   extends RuntimeException
/*     */ {
	
	
/*     */   private static final long serialVersionUID = 1L;
/*     */  private Timestamp timestamp;
			private Integer status;
			private String error;
			private String message;
			private String path;
/*     */  
/*     */
			public Timestamp getTimestamp() {
				return timestamp;
			}
			public AspectException(Integer status, String error, String message) {
				super();
				this.status = status;
				this.error = error;
				this.message = message;
			}
			public void setTimestamp(Timestamp timestamp) {
				this.timestamp = timestamp;
			}
			public Integer getStatus() {
				return status;
			}
			public void setStatus(Integer status) {
				this.status = status;
			}
			public String getError() {
				return error;
			}
			public void setError(String error) {
				this.error = error;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public String getPath() {
				return path;
			}
			public void setPath(String path) {
				this.path = path;
			}
			public static long getSerialversionuid() {
				return serialVersionUID;
			} }

