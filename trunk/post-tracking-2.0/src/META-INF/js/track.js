Ext.regApplication({
    name: 'app',
    tabletStartupScreen: 'tablet_startup.png',
    phoneStartupScreen: 'phone_startup.png',
    icon: 'icon.png',
    glossOnIcon: false,
    launch: function() {

    	var itemOne;
    	var treeStore;
    	var panel;
    	var store;
    	var itemSelected;
    	var overlayTb = new Ext.Toolbar({
            dock: 'top'
        });
    	
    	
    	var overlay = new Ext.Panel({
            floating: true,
            modal: true,
            centered: true,
            width: Ext.is.Phone ? 260 : 260,
            height: Ext.is.Phone ? 220 : 260,
            styleHtmlContent: true,
            dockedItems: overlayTb,
            scroll: 'vertical',
            html: '',
            cls: 'htmlcontent'
        });
    	
    	var formBase = {
    			title: 'Novo',
                scroll: 'vertical',
                url   : 'postObject',
                standardSubmit : false,
                items: [
                    {
    	            xtype: 'fieldset',
    	            title: 'Objeto para rastreio',
    	            defaults: {
    		                 required: true,
    		                 labelAlign: 'left',
    		                 labelWidth: '40%'
    		            },
    	            items: 
    	            	[
    		                {
    		            	   xtype: 'textfield',
    		            	   name : 'numero',
    		            	   label: 'Código de Rastreio',
    		            	   useClearIcon: true,
    		            	   autoCapitalize : true,
    		            	   required: true,
    		            	   placeHolder: 'XX999999999XX'
    		                },
    		                {
    		            	   xtype: 'textfield',
    		            	   name : 'description',
    		            	   label: 'Descrição',
    		            	   useClearIcon: true,
    		            	   autoCapitalize : false,
    		            	   required: true,
    		            	   placeHolder: 'Uma breve descrição'
    		                }
    	                ]
                    }
    		       ],
    		       listeners : {
    	                submit : function(form, result){
    	                    console.log('success', Ext.toArray(arguments));
    	                },
    	                exception : function(form, result){
    	                    console.log('failure', Ext.toArray(arguments));
    	                }
    	            },
    	            dockedItems: [
    					{
    					    xtype: 'toolbar',
    					    dock: 'bottom',
    					    items: [
    	                        {
                                text: 'Reset',
    	                            handler: function() {
    	                                form.reset();
    	                            }
    	                        },
    	                        {
    	                            text: 'Save',
    	                            ui: 'confirm',
    	                            handler: function() {
	    	                                form.submit({
	    	                                    waitMsg : {message:'Enviando', cls : 'demos-loading'},
	    	                                    headers: {Accept: 'application/json'},
	    	                                    success: function(form, action) 
	    	                                    {
	    	                                      Ext.Msg.alert('Success', "Data saved successfully.", Ext.emptyFn);
	    	                                      //store.load();
	    	                                      treeStore.load();
	    	                                    },
	    	                                    failure: function(form, action) 
	    	                                    {
	    	                                    	if(!action.sucess){
		    	                                      var text = '<ul>';
		    	                                      for(var i = 0; i < action.errors.length; i++ ){
		    	                                    	  text+='<li>'+action.errors[i].field+' : '+action.errors[i].message+'</li>';
		    	                                      }
		    	                                      text+= '</ul>';
		    	                                      Ext.Msg.alert('Erro', text, Ext.emptyFn);
	    	                                    	}else{
	    	                                    		Ext.Msg.alert('Success', "Data saved successfully.", Ext.emptyFn);
	  	    	                                      	//store.load();
	    	                                    		treeStore.load();
	    	                                    		panel.show();
	    	                                    	}
	    	                                    }
	    	                                });
    	                            }
    	                        }
    	                    ]
    					}
    	            ]
        	}
    	
    	var form = new Ext.form.FormPanel(formBase);
    	
    	Ext.regModel('Objeto', {
    		idProperty: 'id',
            fields: [
                {name: 'id',     		type: 'string'},
                {name: 'numero',     	type: 'string'},
                {name: 'createDate',    type: 'string'},
                {name: 'statusDate',    type: 'string'},
                {name: 'lastStatusCod', type: 'string'},
                {name: 'lastTipo',      type: 'string'},
                {name: 'lastStatusDesc',type: 'string'},
                {name: 'lastStatusDate',type: 'string'},
                {name: 'open',     		type: 'boolean'},
                {name: 'description',   type: 'string'}
            ],
            validations: [
                  {type: 'format',   name: 'numero', matcher: /[A-Z]{2}[0-9]{9}[A-Z]{2}/},
                  {type: 'presence', name: 'description'},
              ]
        });
    	
    	Ext.regModel('Item', {
    	    idProperty: 'id',
    	    fields: [
    	        {name: 'id',       type: 'string'},
    	        {name: 'text', 	   type: 'string'}
    	    ]
    	});
    	
    	treeStore = new Ext.data.TreeStore({
            model: 'Item',
            proxy: {
                type: 'ajax',
                url: 'getTree',
                reader: {
                    type: 'tree',
                    root: 'children'
                }
            }
        });
    	
    	var nestedList = new Ext.NestedList({
            fullscreen: true,
            title: 'Objetos',
            displayField: 'text',
            // add a / for folder nodes in title/back button
            getTitleTextTpl: function() {
                return '-{' + this.displayField + '}<tpl if="leaf !== true"></tpl>';
            },
            // add a / for folder nodes in the list
            getItemTextTpl: function() {
                return '-{' + this.displayField + '}<tpl if="leaf !== true"></tpl>';
            },
            // provide a codebox for each source file
            getDetailCard: function(record, parentRecord) {
            	return overlay;
            },
            onItemTap(subList,subIdx,el,e ){
            	
            }, 
            store: treeStore
        });
    	
    	nestedList.on('leafitemtap', function(subList, subIdx, el, e, detailCard) {
            var ds = subList.getStore(),
                r  = ds.getAt(subIdx);

            Ext.Ajax.request({
                url: 'getHistory?id=' + r.get('id'),
                success: function(response) {
                    detailCard.setValue(response.responseText);
                },
                failure: function() {
                    detailCard.setValue("Loading failed.");
                }
            });
        });
    	
    	
    	
    	//store.load();
    	//treeStore.load();
    	
    	Ext.regModel('Item', {
            fields: [
                {name: 'text',   type: 'string'}
            ]
        });
    	var item;
    	var storeItem = new Ext.data.Store({
    		 model: 'Item',
    		data : item
    	});
    	
    	var refresh = {iconMask: true, iconCls: 'refresh',
    			listeners: {
    				tap: function() {store.load();}
    			}};
    	
    	var addIcon = {iconMask: true, iconCls: 'add',
    			listeners: {
    				tap: function() {form.show();}
    			}};
    	
    	var makeAjax = function() {
			Ext.getBody().mask('Loading...', 'x-mask-loading', false);
            Ext.util.JSONP.request({
                url: 'delete',
                callbackKey: 'callback',
                params: {                    
                    id : itemSelected,
                    format: 'json'
                },
                callback: function(result) {
                    if(!result.sucess){
                    	Ext.Msg.alert('Erro', 'Nao foi possivel excluir', Ext.emptyFn);
                    	deleteIcon.disabled = true;
                    }
                    Ext.getBody().unmask();
                }
            });
            //panel.items = [objectsList];
			store.load();
			deleteIcon.disabled = true;
		}
    	
    	var deleteIcon = {iconMask: true, iconCls: 'x-icon-mask trash',
    			disabled: true,
    			listeners: {
    				tap: makeAjax
    			}
    	};
    	
    	var listDetail = {
    			height: 500,
    			xtype: 'list',
    			store : storeItem,
    			itemTpl : '<strong>{text}</strong>',
    	}; 
    	
    	
    	itemOne = new Ext.Panel({
    		title: 'Objetos',
    	    fullscreen: true,
    	    layout: Ext.is.Phone ? 'fit' : {
                type: 'vbox',
                align: 'center',
                pack: 'center'
            },
    	    items: [nestedList],
    		dockedItems: [
  					{
  					    xtype: 'toolbar',
  					    dock: 'bottom',
      					layout: {
      	                  pack: 'center'
      					},
  					    items: [
  					         addIcon,   
  					         refresh ,
  					         deleteIcon
  					    ]
  					}
  				]
    	});
    	
    	

    	panel = new Ext.Panel({
    		ui        : 'light',
            fullscreen: true,
            items: [itemOne]
        });
    	
    }
});