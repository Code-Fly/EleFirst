﻿select date_format(`a`.`clientOperationTime`,'%Y%m') AS `days`,`a`.`area_id` AS `area_id`,`a`.`concentrator_id` AS `concentrator_id`,`b`.`name` AS `name`,`a`.`pn` AS `pn`,(max((`a`.`A_Current` + 0)) * `b`.`ct`) AS `maxACurrent`,(max((`a`.`B_Current` + 0)) * `b`.`ct`) AS `maxBCurrent`,(max((`a`.`C_Current` + 0)) * `b`.`ct`) AS `maxCCurrent`,(min((`a`.`A_Current` + 0)) * `b`.`ct`) AS `minACurrent`,(min((`a`.`B_Current` + 0)) * `b`.`ct`) AS `minBCurrent`,(min((`a`.`C_Current` + 0)) * `b`.`ct`) AS `minCCurrent` from (`t_003_type_one_data_fn25` `a` join `t_202_pn_info` `b`) where ((`a`.`area_id` = `b`.`area_id`) and (`a`.`concentrator_id` = `b`.`concentrator_id`) and (`a`.`pn` = `b`.`pn`)) group by `days`,`a`.`area_id`,`a`.`concentrator_id`,`a`.`pn`