SELECT
	id ,
	area_id ,
	concentrator_id ,
	pn ,
	clientOperationTime ,
	MIN(
		CASE rate_seq
		WHEN '1' THEN
			positiveActivePower
		ELSE
			0
		END
	) AS minrateseq1 ,
	MIN(
		CASE rate_seq
		WHEN '2' THEN
			positiveActivePower
		ELSE
			10000
		END
	) AS minrateseq2 ,
	MIN(
		CASE rate_seq
		WHEN '3' THEN
			positiveActivePower
		ELSE
			0
		END
	) AS minrateseq3 ,
	MIN(
		CASE rate_seq
		WHEN '4' THEN
			positiveActivePower
		ELSE
			0
		END
	) AS minrateseq4
FROM
	(
		SELECT
			*
		FROM
			(
				SELECT
					a.area_id ,
					a.concentrator_id ,
					a.pn ,
					a.clientOperationTime ,
					b.*
				FROM
					t_004_type_one_data_fn33 a
				JOIN t_005_type_one_data_fn33_rate b ON a.id = b.id
				WHERE
					(
						(
							`area_id` = '1'
							AND `concentrator_id` = '417'
							AND `pn` = '1'
						)
					)
				AND(
					(
						`clientOperationTime` >= '20170210000000'
						AND `clientOperationTime` < '20170214000000'
					)
				)
				ORDER BY
					a.clientOperationTime ,
					b.positiveActivePower ASC
			) t1
		GROUP BY
			area_id ,
			concentrator_id ,
			pn ,
			rate_seq ,
			LEFT(clientOperationTime , 10)
		ORDER BY
			clientOperationTime DESC
	) t2
GROUP BY
	area_id ,
	concentrator_id ,
	pn ,
	LEFT(clientOperationTime , 10)