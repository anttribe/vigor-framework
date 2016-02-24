var defensor = defensor || {};
$.extend(defensor, {
	resource: {
		listResourcesByParent: function(criteria, callback){
	        $.ajax({
	        	type: 'POST',
	        	async: true,
	        	url: contextPath + '/resource/list/exec',
	        	data: criteria || {},
	        	success: function(resources){
	        		if(callback){
	        			callback.call(this, (resources && $.parseJSON(resources)));
	        		}
	        	}
	        });
		}
	}
});