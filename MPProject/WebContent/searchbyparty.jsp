<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mongodb.DBObject" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Open Parliament India @ OpenHackthon 2010</title>
  <meta name="robots" content="index, follow" />
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <meta name="author" content="RapidxHTML" />
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <!--[if lte IE 7]><link href="css/iehacks.css" rel="stylesheet" type="text/css" /><![endif]-->
  <script type="text/javascript" src="js/jquery.js"></script>
  <!--[if IE 6]>
  <script type="text/javascript" src="js/ie6pngfix.js"></script>
  <script type="text/javascript">
    DD_belatedPNG.fix('img, ul, ol, li, div, p, a, h1, h2, h3, h4, h5, h6');
  </script>
  <![endif]-->
  
  
<link rel="stylesheet" type="text/css" href="css/searchstyle.css" />

<script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js?ver=1.4.2'></script>
<script type="text/javascript">
$(document).ready(function(){ 
	
	// Add the value of "Search..." to the input field
	$("#search").val("Search...");
	
	// When you click on #search
	$("#search").focus(function(){
		
		// If the value is equal to "Search..."
		if($(this).val() == "Search...") {
			// remove all the text
			$(this).val("");	
		}
		
	});
	
	// When the focus on #search is lost
	$("#search").blur(function(){
		
		// If the input field is empty
		if($(this).val() == "") {
			// Add the text "Search..."
			$(this).val("Search...");	
		}
		
	});
	
	$("#search-submit").hover(function(){
		$(this).addClass("hover");
	});

});

function getDetails(mpname)
{
  document.linkform.mpsname.value = mpname ;
  document.linkform.submit() ;
}

</script>

  
  <style type="text/css">
  .style1 {
	  font-size: medium;
	  text-align: center;
  }
  .style2 {
	  text-align: center;
  }
  </style>

  
</head>

<body id="page">

<!-- wrapper -->
<div class="rapidxwpr floatholder">

  <!-- main body -->
  <div id="middle">
  
    <!-- left column -->
    <div id="left" class="clearingfix">
    
      <!-- logo -->
      <a href="index.html">
		<img id="logo" src="images/logo.png" alt="Home" title="Home" height="160" width="150" /></a>
      <!-- / logo -->
      
      <div class="menu">
        <ul>
          <li><a href="index.html" class="home">Home</a></li>
          <li><a href="searchbystate.jsp" class="commitment">Search By State</a></li>
          <li><a href="searchbyparty.jsp" class="organic">Search By Party</a></li>
          <li><a href="searchbyname.jsp" class="delivery">Search By Name</a></li>
          <li><a href="searchbyconstituency.jsp" class="delivery1">Search By Constituency</a></li>
          <li><a href="searchbyage.jsp" class="contact">Search By Age</a></li>
        </ul>
      </div>
    
    </div>
    <!-- / left column -->
    
    <!-- content column -->
    <div id="main">
      <div id="main_container" class="clearingfix">
        <div id="mainmiddle" class="floatbox withright">
        
          <!-- welcome -->
          <div class="welcome">
            <h2>Welcome</h2>
            <p style="width: 452px" class="style1">&nbsp;</p>
						<p style="width: 452px" class="style1"><em><strong>Search your Leader By Party</strong></em></p>
					 <div style="text-align:center">
            <form name="search-form" action="MPServlet" method="post">
          
                    <input type="text" id="search" name="search" style="left: 140px; top: 250px; width: 150px" />
                   <input type="hidden" name="pagename" value="party"/>
                    <input type="submit" id="search-submit" value="" style="left: 300px; top: 240px;" />
              
            </form>
		</div>
            <% ArrayList mps = (ArrayList)request.getAttribute("mps");
           if(mps==null)
           { %>
            
            <div id="results" style="padding-top:260px" class="style2">
            <%}else{ %>
            <div id="results" style="padding-top:100px" class="style2">
           <form name="linkform" action="DetailServlet" method="post">
           <input type="hidden" name="mpsname" />
           <%
           	  Iterator mpsIterator = mps.iterator();
           	  while(mpsIterator.hasNext())
           	  {
           		DBObject userdetails = (DBObject)mpsIterator.next();
           		Set fields = userdetails.keySet();
           		Iterator it = fields.iterator();
           		while(it.hasNext())
           		{
           			String fieldName = (String) it.next();
           			if(fieldName.equalsIgnoreCase("Name"))
           			{
           				String name = (String)userdetails.get(fieldName);
           %>
           
			<p><a href="javascript:getDetails('<%=name %>')"><%=name %></a></p>
			<%} }
			}
           	  } %>	
           	  </form>			
		   </div> 
		   
                     
            </div>
          </div>
          <!-- / welcome -->
          
         
        
        
        </div>
      </div>
    </div>
    <!-- / content column -->
  
  </div>
  <!-- / main body -->
  
  <!-- footer -->
  <div id="footer">
      © openparliamentindia | <a href="http://studio7designs.com/network">Designed by STUDIO7DESIGNS</a>
  </div>
  <!-- / footer -->

</div>
<!-- / wrapper -->

</body>
</html>