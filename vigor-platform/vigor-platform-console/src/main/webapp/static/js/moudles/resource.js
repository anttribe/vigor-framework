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
		},
		resourceSelector: function(options){
			return new BootstrapDialog({
				size: BootstrapDialog.SIZE_NORMAL,
				type: BootstrapDialog.TYPE_DEFAULT,
				draggable: true,
				closable: true,
	            title: (options && options.title) || '',
	            message: $('<div></div>').load(contextPath + '/resource/tool/selector'),
	            buttons: [{
	            	label: BootstrapDialog.DEFAULT_TEXTS.CANCEL,
	            	action: function(dialogRef) {
	            		dialogRef.close();
	                }
	            }]
	        });
		},
		selectResource: function(resource){
    		if(resource){
    			(window || window.parent).selectResource(resource);
    		}
    	}
	}
});