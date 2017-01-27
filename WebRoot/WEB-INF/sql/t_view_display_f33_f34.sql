CREATE VIEW t_view_display_f33_f34 AS SELECT
  a.totalPositiveActivePower,a.totalPositiveReactivePower,a.id as id33,a.area_id as area_id33,a.clientOperationTime as clientOperationTime33,a.concentrator_id as concentrator_id33,a.pn as pn33,a.rate as rate33,a.sendTime as sendTime33,
  b.totalReverseActivePower,b.totalReverseReactivePower,b.id as id34,b.area_id as area_id34,b.clientOperationTime as clientOperationTime34,b.concentrator_id as concentrator_id34,b.pn as pn34,b.rate as rate34,b.sendTime as sendTime34
FROM
	t_004_type_one_data_fn33 a,
	t_006_type_one_data_fn34 b
WHERE
	a.concentrator_id = b.concentrator_id
AND a.pn = b.pn
AND a.area_id = b.area_id
AND a.clientOperationTime = b.clientOperationTime and a.clientOperationTime is NOT null 
AND b.clientOperationTime is not NULL