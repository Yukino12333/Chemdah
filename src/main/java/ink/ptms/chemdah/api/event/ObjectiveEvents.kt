package ink.ptms.chemdah.api.event

import ink.ptms.chemdah.core.PlayerProfile
import ink.ptms.chemdah.core.quest.Task
import ink.ptms.chemdah.core.quest.Template
import ink.ptms.chemdah.core.quest.objective.Objective
import io.izzel.taboolib.module.event.EventNormal
import org.bukkit.Bukkit

/**
 * Chemdah
 * ink.ptms.chemdah.api.event.QuestEvents
 *
 * @author sky
 * @since 2021/2/21 1:07 上午
 */
class ObjectiveEvents {

    /**
     * 当条目继续时
     */
    class Continue(val objective: Objective<*>, val task: Task, val playerProfile: PlayerProfile): EventNormal<Continue>() {

        init {
            async(!Bukkit.isPrimaryThread())
        }
    }

    /**
     * 当条目完成时
     */
    class Complete(val objective: Objective<*>, val task: Task, val playerProfile: PlayerProfile): EventNormal<Complete>() {

        init {
            async(!Bukkit.isPrimaryThread())
        }
    }

    /**
     * 当条目重置时
     */
    class Reset(val objective: Objective<*>, val task: Task, val playerProfile: PlayerProfile): EventNormal<Reset>() {

        init {
            async(!Bukkit.isPrimaryThread())
        }
    }
}