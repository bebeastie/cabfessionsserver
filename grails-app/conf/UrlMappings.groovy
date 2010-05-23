class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/"(view:"/index")
	  "500"(view:'/error')
	  
	  "/api/login" {
    	  controller = "apiMain"
    	  action = "login"
      }
      
      "/api/cabfession/by_badge" {
    	  controller = "apiCabfession"
    	  action = "by_badge"
      }

      "/api/cabfession/by_location" {
    	  controller = "apiCabfession"
    	  action = "by_location"
      }
      
      "/api/cabfession/by_tag" {
    	  controller = "apiCabfession"
    	  action = "by_tag"
      }
      
      "/api/cabfession/create" {
    	  controller = "apiCabfession"
    	  action = "create"
      }
      
      "/api/cabfession/tag" {
    	  controller = "apiCabfession"
    	  action = "tag"
      }
      
      "/api/comment/create" {
    	  controller = "apiComment"
    	  action = "create"
      }
      
      "/api/comment/vote" {
    	  controller = "apiComment"
    	  action = "vote"
      }
      
      "/api/comment/list" {
    	  controller = "apiComment"
    	  action = "list0"
      }
      
      "/api/feedback/create" {
    	  controller = "apiFeedback"
    	  action = "create"
      }
      
	}
}
