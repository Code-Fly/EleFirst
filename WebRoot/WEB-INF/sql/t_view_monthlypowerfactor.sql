﻿select date_format(`a`.`clientOperationTime`,'%Y%m') AS `days`,`a`.`area_id` AS `area_id`,`a`.`concentrator_id` AS `concentrator_id`,`b`.`name` AS `name`,`a`.`pn` AS `pn`,`b`.`power_factor_standard` AS `power_factor_standard`,round(avg((`a`.`totalPowerFactor` + 0)),0) AS `avgTotalPowerFactor`,max((`a`.`A_PowerFactor` + 0)) AS `aMaxPowerFactor`,max((`a`.`B_PowerFactor` + 0)) AS `bMaxPowerFactor`,max((`a`.`C_PowerFactor` + 0)) AS `cMaxPowerFactor`,max((`a`.`totalPowerFactor` + 0)) AS `maxTotalPowerFactor`,min((`a`.`A_PowerFactor` + 0)) AS `aMinPowerFactor`,min((`a`.`B_PowerFactor` + 0)) AS `bMinPowerFactor`,min((`a`.`C_PowerFactor` + 0)) AS `cMinPowerFactor`,min((`a`.`totalPowerFactor` + 0)) AS `minTotalPowerFactor` from (`t_003_type_one_data_fn25` `a` join `t_202_pn_info` `b`) where ((`a`.`area_id` = `b`.`area_id`) and (`a`.`concentrator_id` = `b`.`concentrator_id`) and (`a`.`pn` = `b`.`pn`)) group by `days`,`a`.`area_id`,`a`.`concentrator_id`,`a`.`pn`