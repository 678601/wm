package wm.demo.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterUrlConfig {
		
		@Value("${filter.urls}")
		private String[] urls;

		public String[] getUrls() {
			return urls;
		}

		public void setUrls(String[] urls) {
			this.urls = urls;
		}
		
	
}
