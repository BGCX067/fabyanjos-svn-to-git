/**
 * Verifica se os caracteres inseridos no campo data são válidos (números e barras)
 * @param pField
 * @param pMaxLen
 * @param pPressKey
 */

function formatDate(pField,pMaxLen,pPressKey) {
	fieldValue	= pField.value.trim();

	fieldValue	= fieldValue.replace('/','');
	fieldValue	= fieldValue.replace('/','');
	fieldValue	= fieldValue.replace('/','');

	fieldLen	= fieldValue.length;

	if(((fieldLen+1) != 3 && pPressKey == 111) || ((fieldLen+1) != 6 && pPressKey == 111))
		window.event.returnValue = false;

	if(((fieldLen+1) != 3 && pPressKey == 193) || ((fieldLen+1) != 6 && pPressKey == 193))
		window.event.returnValue = false;

	if(fieldLen < pMaxLen && pPressKey != 8)
		fieldLen = fieldValue.length + 1;

	if(pPressKey == 8)
		fieldLen = fieldLen - 1;

	if(pPressKey == 8 || pPressKey >= 48 && pPressKey <= 57 || pPressKey >= 96 && pPressKey <= 105) {
		if(fieldLen <= 2)
			pField.value = fieldValue;

		if(fieldLen == 3)
			pField.value = fieldValue.substr(0,(fieldLen-1)) + '/' + fieldValue.substr((fieldLen-1),fieldLen);

		if(fieldLen == 5)
			pField.value = fieldValue.substr(0,(fieldLen-3)) + '/' + fieldValue.substr((fieldLen-3),2) + '/';
	}

	isDateValidCharacter(pPressKey);
}

/**
 * Verifica se a data é válida
 * @param pObj
 */
function dateVerify(pObj) {
	objValue = pObj.value.trim();
	if (objValue == '') return true;
	month30	 = '04060911';

	if(objValue == '')
		return false;

	if(objValue.length != 10)
		return dateError(pObj);

	iDay	= parseInt(objValue.substring(0,2),10);
	iMonth	= parseInt(objValue.substring(3,5),10);
	iYear	= parseInt(objValue.substring(6,10),10);

	if(iYear < 1900 || iYear > 2050)
		return dateError(pObj);

	if(iMonth < 1 || iMonth > 12)
		return dateError(pObj);

	if(iDay < 1 || iDay > 31)
		return dateError(pObj);

	if(iMonth == 2) {
		iBi = iYear % 4;

		if(iBi == 0) {
			if(iDay > 29)
				return dateError(pObj);

			return true;
		}

		if(iDay > 28)
		    return dateError(pObj);
	}

	if(iMonth.length != 2)
		iMonth = '0' + iMonth;

	if(month30.indexOf(iMonth) > 0)
		if(iDay > 30)
		    return dateError(pObj);

	return true;
}













/**
 * Verifica se o e-mail é válido
 * @param pEmail
 */
function emailVerify(pEmail) {
    email  = pEmail.value.trim();
    filter = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

    if(filter.test(email) || email == '')
        return true;

    alert('Favor inserir um e-mail válido');
    pEmail.select();

    return false;
}